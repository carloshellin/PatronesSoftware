package objetos;

import components.HabitacionGraphicsComponente;
import juego.Direccion;
import juego.ListaTesoros;
import juego.Mundo;

public class Habitacion extends Escenario
{  
    private Habitacion norte, sur, este, oeste;
    private final ListaTesoros objetos;
    private Monstruo monstruo;
    
    public Habitacion(String nombre, String descripcion, String imagen)
    {
        super(nombre, descripcion, new HabitacionGraphicsComponente(imagen));
        
        norte = null;
        sur = null;
        este = null;
        oeste = null;
        
        Mundo mundo = Mundo.getInstancia();
        
        setX(mundo.getVentanaAncho() / 2);
        setY(0);
        
        this.objetos = new ListaTesoros();
        this.monstruo = null;
    }
    
    public void conectar(Direccion direccion, Habitacion habitacion)
    {
        switch (direccion)
        {
            case NORTE:
            {
                norte = habitacion;
            } break;
            
            case SUR:
            {
                sur = habitacion;
            } break;
            
            case ESTE:
            {
                este = habitacion;
            } break;
            
            case OESTE:
            {
                oeste = habitacion;
            } break;
        }
    }   
    
    public String descripcion()
    {
        String mensaje = String.format("%s\n", getDescripcion())
                + objetos.describir();
        
        if (monstruo != null)
        {
            mensaje += "\n" + monstruo.descripcion();
        }
        
        return mensaje;
    }

    public Habitacion getNorte()
    {
        return norte;
    }

    public Habitacion getSur()
    {
        return sur;
    }

    public Habitacion getEste()
    {
        return este;
    }

    public Habitacion getOeste()
    {
        return oeste;
    }
    
    public Habitacion getHabitacion(Direccion direccion)
    {
        switch (direccion)
        {
            case NORTE:
            {
                return norte;
            }
            
            case SUR:
            {
                return sur;
            }
            
            case ESTE:
            {
                return este;
            }
            
            case OESTE:
            {
                return oeste;
            }
        }
        
        return null;
    }
    
    public ListaTesoros getObjetos()
    {
        return objetos;
    }
    
    public void addObjeto(Tesoro tesoro) 
    {
        objetos.add(tesoro);
    }

    public void setMonstruo(Monstruo monstruo) 
    {
        this.monstruo = monstruo;
    }

    public Monstruo getMonstruo()
    {
        return monstruo;
    }
}