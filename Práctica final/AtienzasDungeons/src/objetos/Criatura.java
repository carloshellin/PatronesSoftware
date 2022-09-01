package objetos;

import atributos.Atributo;
import components.GraphicsComponente;
import juego.Dado;
import juego.Entidad;
import juego.Mundo;

public abstract class Criatura extends Entidad
{
    protected Atributo fuerza;
    protected Atributo destreza;
    protected Atributo constitucion;
    protected Atributo inteligencia;
    protected Atributo sabiduria;
    protected Atributo carisma;
    protected final String nombre;
    protected final String raza;
    protected final String clase;
    protected int puntosGolpe;
    protected int claseArmadura;
    protected int bonificadorCompetencia;
    protected boolean esquivando;
    protected String objeto;
    
    public Criatura(String nombre, String raza, String clase,
            GraphicsComponente graphicsComponente)
    {
        super(graphicsComponente);
        
        this.nombre = nombre;
        this.raza = raza;
        this.clase = clase;
        this.esquivando = false;
    }
    
    public void atacar()
    {
        Mundo mundo = Mundo.getInstancia();
        esquivando = false;
        
        int bonificador = getBonificador();
        
        boolean esquiva = getObjetivoEsquiva();
        
        Dado dado = new Dado(1, 20);
        int tirada;
        
        if (esquiva)
        {
            int primera = dado.tirar();
            int segunda = dado.tirar();
            
            if (primera >= segunda)
            {
                tirada = segunda;
            }
            else
            {
                tirada = primera;
            }
            
            mundo.mostrar(getNombre() + " intenta atacar al evasor " 
                    + "2d20: [" + primera + ", " + segunda + "]"
                            + " = " + tirada);
        }
        else
        {
            tirada = dado.tirar();
        }
        
        int resultado = tirada + bonificador;
        
        int CA = getObjetivoCA();
        
        if (resultado >= CA)
        {
            int daño = getDaño();
            mundo.mostrar(getNombre() + " hace " + daño + " de daño. "
                    + "(Impacto: 1d20+" + bonificador + " = " + resultado + ")");
            objetivoImpacto(daño);
        }
        else
        {
            mundo.mostrar(getNombre() + " falla el ataque."
                    + "(Impacto: 1d20+" + bonificador + " = " + resultado + ")");
        }
    }
    
    public void esquivar()
    {
        esquivando = true;
     
        Mundo mundo = Mundo.getInstancia();
        mundo.mostrar(getNombre() + " se concentra en esquivar el próximo "
                + "ataque.");
    }
    
    public int getBonificador()
    {
        return getAtributoAtaque() + bonificadorCompetencia;
    }
    
    public void impacto(int daño)
    {
        puntosGolpe -= daño;
    }
    
    public String getNombre()
    {
        return nombre;
    }

    public int getClaseArmadura()
    {
        return claseArmadura;
    }

    public boolean getEsquivando()
    {
        return esquivando;
    }

    public abstract String descripcion();
    
    public abstract int getAtributoAtaque();
    
    public abstract int getObjetivoCA();
    
    public abstract void objetivoImpacto(int daño);
    
    public abstract boolean getObjetivoEsquiva();
    
    public abstract int getDaño();
}
