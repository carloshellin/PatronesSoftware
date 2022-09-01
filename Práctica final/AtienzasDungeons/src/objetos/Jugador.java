package objetos;

import components.NullGraphicsComponente;
import java.util.ArrayList;
import juego.Dado;
import juego.Direccion;
import juego.Entidad;
import juego.ListaTesoros;
import juego.Mundo;
import juego.Recursos;

public class Jugador extends Entidad
{
    private Habitacion localizacion;
    private final ListaTesoros inventario;
    private Personaje personaje;
    
    public Jugador()
    {
        super(new NullGraphicsComponente());
        
        this.localizacion = null;
        this.inventario = new ListaTesoros();
    }
    
    public void mirar()
    {
        Mundo mundo = Mundo.getInstancia();
        mundo.mostrar(localizacion.descripcion());
    }
        
    public void mover(Direccion direccion)
    {
        boolean pasilloOeste = localizacion.getNombre().contains("PasilloOeste");
        
        localizacion = localizacion.getHabitacion(direccion);
        
        if (pasilloOeste && localizacion.getNombre().contains("MadrigueraOeste"))
        {
            Recursos.stop();
            Recursos.reproducir("final");
        }
        
        mirar();
    }
    
    private void transferir(Tesoro tesoro, ListaTesoros desde, ListaTesoros a)
    {
        desde.remove(tesoro);
        a.add(tesoro);
    }
    
    public void coger(String nombre)
    {
        Mundo mundo = Mundo.getInstancia();
        ListaTesoros objetos = localizacion.getObjetos();
        Tesoro objeto = objetos.get(nombre);
        
        if (nombre.equals(""))
        {
            mundo.mostrar("Tienes que indicar el nombre del objeto a coger");
        }
        else if (objeto == null)
        {
            mundo.mostrar("No hay " + nombre);
        }
        else
        {
            if (nombre.contains("necronomicón"))
            {
                localizacion = mundo.getHabitacion("Final");
                mundo.mostrar(localizacion.getDescripcion());
                return;
            }
            
            if (nombre.contains("espada")) personaje.setEquipo(objeto);
            transferir(objeto, objetos, inventario);
            mundo.mostrar("Has cogido " + nombre);
        }
    }
    
    public void soltar(String nombre)
    {
        Mundo mundo = Mundo.getInstancia();
        ListaTesoros objetos = localizacion.getObjetos();
        Tesoro objeto = inventario.get(nombre);
        
        if (nombre.equals(""))
        {
            mundo.mostrar("Tienes que indicar el nombre del objeto a soltar");
        }
        else if (objeto == null)
        {
            mundo.mostrar("No tienes " + nombre);
        }
        else
        {
            if (nombre.contains("espada")) personaje.setEquipo(null);
            transferir(objeto, inventario, objetos);
            mundo.mostrar("Has soltado " + nombre);
        }
    }
    
    public void usoConcreto(String nombre, String objeto, String habitacion,
            Runnable funcion)
    {
        Mundo mundo = Mundo.getInstancia();
        String localizacionNombre = localizacion.getNombre();
        
        if (nombre.contains(objeto))
        {
            if (inventario.get(objeto) == null)
            {
                mundo.mostrar("No tienes una " + objeto + " en el inventario.");
            }
            else if (localizacionNombre.startsWith(habitacion))
            {
                funcion.run();
            }
            else
            {
                mundo.mostrar("Aquí no sirve usar la " + objeto + ".");
            }
        }
    }
    
    public void usar(String nombre) 
    {
        Mundo mundo = Mundo.getInstancia();
        String localizacionNombre = localizacion.getNombre();
        
        Runnable antorcha = () -> 
        {
            Habitacion habitacion = mundo.getHabitacion(localizacionNombre
                    .replace("Oscuro", ""));
            localizacion = habitacion;
            
            Habitacion pasilloNorte = mundo.getHabitacion("PasilloNorte");
            mundo.getHabitacion("VestibuloNorte").conectar(Direccion.NORTE,
                    pasilloNorte);
            
            Habitacion pasilloOeste = mundo.getHabitacion("PasilloOeste");
            mundo.getHabitacion("CavernaNorte").conectar(Direccion.NORTE,
                    pasilloOeste);
            
            mundo.mostrar("Usas la antorcha para ver mejor.");
        };
        
        Runnable llave = () -> 
        {
            Habitacion habitacion = mundo.getHabitacion("MadrigueraOeste");
            localizacion.conectar(Direccion.OESTE, habitacion);
            mundo.mostrar("Abres la puerta.");
        };
        
        if (nombre.equals(""))
        {
            mundo.mostrar("Tienes que indicar el nombre del objeto a usar");
        }
        else if (nombre.contains("poción"))
        {
            Tesoro pocion = inventario.get("poción");
            if (pocion == null)
            {
                mundo.mostrar("No tienes una poción en el inventario.");
            }
            else
            {
                personaje.curar();
                inventario.remove(pocion);
                mundo.mostrar("Usas la poción para curarte hasta tus puntos"
                        + " máximos");
            }
        }
        else
        {
            usoConcreto(nombre, "antorcha", "PasilloOscuro", antorcha);
            usoConcreto(nombre, "llave", "PasilloOeste", llave);
        }
    }
    
    public void inventario()
    {
        Mundo mundo = Mundo.getInstancia();

        String cadena;

        if (inventario.isEmpty())
        {
            cadena = "No hay objetos en el inventario.";
        }
        else
        {
            cadena = "Tienes en el inventario:\n" + inventario.tesoros();
        }

        mundo.mostrar(cadena);
    }

    public void setLocalizacion(Habitacion localizacion)
    {
        this.localizacion = localizacion;
    }

    public Habitacion getLocalizacion()
    {
        return localizacion;
    }    

    public ArrayList<Tesoro> getInventario()
    {
        return inventario;
    }

    public void setPersonaje(Personaje personaje)
    {
        this.personaje = personaje;
    }

    public int getCA()
    {
        return personaje.claseArmadura;
    }

    public void impacto(int daño)
    {
        personaje.impacto(daño);
    }
    
    public void accion(boolean esquivar)
    {
        Monstruo monstruo = localizacion.getMonstruo();
        if (monstruo != null)
        {
            if (esquivar)
            {
                personaje.esquivar();
            }
            else
            {
                personaje.atacar();
            }
           
            
            monstruo.IA();
        }
        else
        {
            Mundo mundo = Mundo.getInstancia();
            mundo.mostrar("Aquí no hay ningún monstruo.");
        }
    }
    
    public void chimenea(String accion, String origen, String destino)
    {
        Mundo mundo = Mundo.getInstancia();
        
        if (localizacion.getNombre().contains(origen))
        {
            Dado dado = new Dado(1, 20);
            int tirada = dado.tirar();
            String mensaje;
            if (tirada >= 10)
            {
                mensaje = "Consigues " + accion + " por la chimenea";
                localizacion = mundo.getHabitacion(destino);
                mirar();
            }
            else if (tirada <= 5)
            {
                dado = new Dado(1, 6);
                int daño = dado.tirar();
                mensaje = "Intentas " + accion + " pero caes y sufres " + daño + " de daño";
                impacto(daño);
                if (accion.contains("descender")) localizacion = mundo.getHabitacion(destino);
            }
            else
            {
                mensaje = "No consigues " + accion + " por la chimenea";
            }
            
            mensaje += " (Tirada: 1d20 = " + tirada + ")";
            
            mundo.mostrar(mensaje);
        }
        else
        {
            mundo.mostrar("Aquí no se puede " + accion + ".");
        }
    }
    
    public void escalar(String accion) 
    {
        if (accion.contains("ascender"))
        {
            chimenea(accion, "Chimenea", "CavernaEste");
        }
        else
        {
            chimenea(accion, "CavernaOeste", "PerreraOeste");
        }
    }
    
    public boolean getEsquivando() 
    {    
        return personaje.esquivando;
    }

    public Personaje getPersonaje() 
    {
        return personaje;
    }
}
