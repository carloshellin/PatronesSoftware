package objetos;

import components.GraphicsComponente;
import juego.Dado;
import juego.Mundo;

public abstract class Personaje extends Criatura
{
    private Tesoro equipo;
    protected int puntosGolpeMax;
    
    public Personaje(String nombre, String raza, String clase, int puntosGolpe,
            GraphicsComponente graphicsComponente) {
        super(nombre, raza, clase, graphicsComponente);
        
        this.equipo = null;
        this.puntosGolpe = puntosGolpe;
        this.puntosGolpeMax = puntosGolpe;
    }
    
    @Override
    public String descripcion()
    {
        return nombre + " es un " + raza + " cuya clase es " + clase + " con"
                + " los siguientes atributos: Fuerza: " + fuerza +", "
                + "Destreza: " + destreza + ", Constitución: "
                + constitucion + ", Inteligencia: " + inteligencia + ", "
                + "Sabiduría: " + sabiduria + " y "
                + "Carisma: " + carisma + " y con " + puntosGolpe 
                + " puntos de golpe";
    }

    @Override
    public int getObjetivoCA() 
    {
        Mundo mundo = Mundo.getInstancia();
        Monstruo monstruo = mundo.getLocalizacion().getMonstruo();
        return monstruo.getClaseArmadura();
    }

    @Override
    public void objetivoImpacto(int daño) 
    {
        Mundo mundo = Mundo.getInstancia();
        mundo.monstruoImpacto(daño);
    }

    @Override
    public boolean getObjetivoEsquiva()
    {
        Mundo mundo = Mundo.getInstancia();
        Monstruo monstruo = mundo.getLocalizacion().getMonstruo();
        return monstruo.getEsquivando();
    }
    
    @Override
    public int getDaño()
    {
        if (equipo == null)
        {
            return 1 + fuerza.calcularValor();
        }
        
        return new Dado(1, 12).tirar() + getAtributoAtaque();
    }
    
    public void setEquipo(Tesoro objeto) 
    {
        equipo = objeto;
    }

    @Override
    public void impacto(int daño) 
    {
        super.impacto(daño);
        
        if (puntosGolpe <= 0)
        {
            Mundo mundo = Mundo.getInstancia();
            mundo.mostrar("Tus puntos de golpe han llegado a " + puntosGolpe 
                    + " y has muerto.");
            Habitacion habitacion = mundo.getHabitacion("Título");
            mundo.setLocalizacion(habitacion);
            
            mundo.nuevo();
        }
    }

    void curar() 
    {
        puntosGolpe = puntosGolpeMax;
    }
    
    
}
