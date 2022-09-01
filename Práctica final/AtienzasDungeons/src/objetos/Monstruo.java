package objetos;

import components.GraphicsComponente;
import juego.Dado;
import juego.Mundo;

public abstract class Monstruo extends Criatura
{

    public Monstruo(String nombre, String raza, String clase, 
            GraphicsComponente graphicsComponente)
    {
        super(nombre, raza, clase, graphicsComponente);
    }
    
    @Override
    public String descripcion()
    {
        return "Ves un " + nombre;
    }

    @Override
    public int getObjetivoCA()
    {
        Mundo mundo = Mundo.getInstancia();
        return mundo.getJugadorCA();
    }
    
    @Override
    public void objetivoImpacto(int daño)
    {
        Mundo mundo = Mundo.getInstancia();
        mundo.jugadorImpacto(daño);
    }
    
    @Override
    public boolean getObjetivoEsquiva()
    {
        Mundo mundo = Mundo.getInstancia();
        return mundo.getJugadorEsquivando();
    }

    @Override
    public void impacto(int daño) 
    {
        super.impacto(daño);
        
        if (puntosGolpe <= 0)
        {
            Mundo mundo = Mundo.getInstancia();
            mundo.mostrar(getNombre() + " muere con " + puntosGolpe 
                    + " puntos de golpe.");
            mundo.getLocalizacion().setMonstruo(null);
            soltar(objeto);
        }
    }
    
    public void IA()
    {
        if (puntosGolpe > 0)
        {
            Dado dado = new Dado(1, 100);
            if (dado.tirar() >= 25)
            {
                atacar();    
            }
            else
            {
                esquivar();
            }   
        }
    }
    
    public void soltar(String objeto) 
    {
        Mundo mundo = Mundo.getInstancia();
        
        Habitacion habitacion = mundo.getHabitacion("SinSalida");
        Tesoro tesoro = habitacion.getObjetos().get(objeto);
        
        Habitacion localizacion = mundo.getLocalizacion();
        
        localizacion.addObjeto(tesoro);
        mundo.mostrar(localizacion.getObjetos().describir());
    }
}
