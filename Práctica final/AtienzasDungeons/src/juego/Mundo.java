package juego;

import interfaz.Ventana;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import juego.Factoria.TipoEnemigo;
import juego.Factoria.TipoPersonaje;
import objetos.Consola;
import objetos.Fondo;
import objetos.Habitacion;
import objetos.Jugador;
import objetos.Monstruo;
import objetos.Personaje;
import objetos.Tesoro;

public class Mundo implements Serializable
{
    private static Mundo instancia;
    private final long FPS = 60L;
    
    private Ventana ventana;
    private ArrayList<Entidad> entidades;
    private Graphics g;
    private Graphics bg;
    private Consola consola;
    private Jugador jugador;
    private Mapa mapa;
    private Factoria factoria;
    private ArrayList<Personaje> personajes;
    
    public static Mundo getInstancia()
    {
        if (instancia == null)
        {
            instancia = new Mundo();
        }
        
        return instancia;
    }
    
    public void iniciar()
    {  
        entidades = new ArrayList<>();
        
        ventana = new Ventana("Atienza's Dungeons", 1280, 720);
        
        g = ventana.getGraphics();
        bg = ventana.getBuffer().getGraphics();
        
        consola = new Consola();
        factoria = new Factoria();
        personajes = new ArrayList<>();
        
        nuevo();
    }
    
    public void nuevo()
    {
        entidades.clear();
        
        Fondo fondo = new Fondo();
        entidades.add(fondo);
        jugador = new Jugador();
        
        mapa = new Mapa("habitaciones.txt", "conexiones.txt", "tesoros.txt",
                "monstruos.txt");
        
        mapa.getHabitaciones().entrySet().forEach(entrada -> {
            entidades.add(entrada.getValue());
        });
        
        Habitacion titulo = mapa.getHabitaciones().get("Título");
        jugador.setLocalizacion(titulo);
        
        personajes.clear();
        personajes.add(factoria.getPersonaje(TipoPersonaje.Dijkstra));
        personajes.add(factoria.getPersonaje(TipoPersonaje.Kerry));
        
        mostrar(titulo.getDescripcion());
        
        Recursos.stop();
        Recursos.reproducir("titulo");
    }
    
    public void guardar()
    {
        try
        {
            FileOutputStream fos = Recursos.escribirPartida("partida.sav");
            try (ObjectOutputStream oos = new ObjectOutputStream(fos))
            {
                oos.writeObject(jugador);
                oos.flush();
            }
            consola.mostrar("Partida guardada.");
        }
        catch (IOException e)
        {
            System.out.print("Error de Serialization! No se pueden guardar los "
                    + "datos.\n" + e.getClass() + ": " + e.getMessage() + "\n");
        }
    }
    
    public void cargar()
    {
        try
        {
            FileInputStream fis = Recursos.leerPartida("partida.sav");
            if (fis == null)
            {
                mostrar("No existe ninguna partida guardada.");
                return;
            }
            try (ObjectInputStream ois = new ObjectInputStream(fis))
            {
                jugador = (Jugador) ois.readObject();
                if (jugador.getLocalizacion().getNombre().startsWith("Madriguera"))
                {
                    Recursos.stop();
                    Recursos.reproducir("final");
                }
                else
                {
                    Recursos.stop();
                    Recursos.reproducir("juego");
                }
            }
            consola.mostrar("Partida cargada.");
        }
        catch (IOException | ClassNotFoundException e)
        {
            System.out.print("Error de Serialization! No se pueden cargar los "
                    + "datos.\n");
            System.out.print(e.getClass() + ": " + e.getMessage() + "\n");
        }
    }

    private void renderizar()
    {
        BufferedImage buffer = ventana.getBuffer();
        
        for (int i = 0; i < entidades.size(); i++)
        {
            entidades.get(i).renderizar(bg);
        }
        
        Insets borderInterior = ventana.getInsets();
        g.drawImage(buffer, borderInterior.left, borderInterior.top, ventana);
        
        ventana.intercambiar();
    }
    
    public void bucleJuego()
    {
        while (true)
        {
            long tiempo = System.nanoTime();
            
            renderizar();
            
            long tiempoPasado = System.nanoTime() - tiempo;
            long tiempoDormir = (1000000000 / FPS) - tiempoPasado;
            
            if (tiempoDormir > 0)
            {
                try 
                {
                    sleep(tiempoDormir / 1000000);
                }
                catch (InterruptedException ex)
                {
                    Logger.getLogger(Mundo.class.getName()).log(Level.SEVERE,
                            null, ex);
                }
            }
        }
    }
    
    public void coger(String nombre)
    {
        jugador.coger(nombre);
    }
    
    public void soltar(String nombre)
    {
        jugador.soltar(nombre);
    }
    
    public void inventario()
    {
        jugador.inventario();
    }
    
    public void mirar()
    {
        jugador.mirar();
    }
    
    public void mostrar(String texto)
    {
        consola.mostrar(texto);
    }
    
    public void mover(Direccion direccion)
    {
        jugador.mover(direccion);
    }
    
    public void info()
    {
        mostrar("Los personajes a elegir son:");
        for (int i = 0; i < personajes.size(); i++)
        {
            mostrar((i + 1) + ". " + personajes.get(i).descripcion());
        }
        mostrar("Escribe 'elegir' seguido del número del personaje para empezar"
                + " con ese personaje.");
    }
    
    public void elegir(int numero)
    {
        if (numero >= 1 && numero <= personajes.size())
        {                    
            Personaje personaje = personajes.get(numero - 1);
            jugador.setPersonaje(personaje);
            mostrar("Has elegido el personaje: " + personaje.getNombre());
            jugador.mover(Direccion.NORTE);
            
            Recursos.stop();
            Recursos.reproducir("juego");
        }
        else
        {
            mostrar("No es un número válido.");
        }
    }
    
    public void usar(String nombre)
    {
        jugador.usar(nombre);
    }
    
    public void atacar() 
    {
        jugador.accion(false);
    }
    
    public void esquivar() 
    {
        jugador.accion(true);
    }
    
    public void escalar(String accion)
    {
        jugador.escalar(accion);
    }
    
    public void jugadorImpacto(int daño)
    {
        jugador.impacto(daño);
    }
    
    public void monstruoImpacto(int daño)
    {
        Monstruo monstruo = jugador.getLocalizacion().getMonstruo();
        monstruo.impacto(daño);
    }
    
    public Monstruo crearMonstruo(String tipo)
    {
        TipoEnemigo enemigo = TipoEnemigo.valueOf(tipo);
        return factoria.getEnemigo(enemigo);
    }
   
    public void addInventario(Tesoro tesoro)
    {
        jugador.getInventario().add(tesoro);
    }
    
    public Tesoro getInventario(String nombre)
    {
        for (Tesoro tesoro : jugador.getInventario())
        {
            if (tesoro.getNombre().toLowerCase().contains(nombre))
            {
                return tesoro;
            }
        }
        
        return null;
    }

    public Habitacion getLocalizacion()
    {
        return jugador.getLocalizacion();
    }

    public void setLocalizacion(Habitacion habitacion)
    {
        jugador.setLocalizacion(habitacion);
    }

    public JTextField getEntrada()
    {
        return ventana.getEntrada();
    }

    public JTextArea getSalida()
    {
        return ventana.getSalida();
    }

    public int getVentanaAncho()
    {
        return ventana.getAncho();
    }
    
    public int getVentanaAlto()
    {
        return ventana.getAlto();
    }

    public int getJugadorCA()
    {
        return jugador.getCA();
    }

    public boolean getJugadorEsquivando()
    {
        return jugador.getEsquivando();
    }
    
    public Habitacion getHabitacion(String nombre)
    {
        return mapa.getHabitaciones().get(nombre);
    }

    public String getJugadorDescripcion() 
    {
        return jugador.getPersonaje().descripcion();
    }
}
