package juego;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Habitacion;
import objetos.Monstruo;
import objetos.Tesoro;

public class Mapa implements Serializable
{
    private HashMap<String, Habitacion> habitaciones;
    
    public Mapa(String habitaciones, String conexiones, String tesoros,
            String monstruos)
    {
        this.habitaciones = new HashMap<>();
        
        Consumer<String[]> crearHabitacion = cadena -> 
        {
            String titulo = cadena[0];
            String descripcion = cadena[1];
            String imagen = cadena[2];

            Habitacion habitacion = new Habitacion(titulo, descripcion, imagen);
            this.habitaciones.put(titulo, habitacion);
        };
        
        Consumer<String[]> crearConexiones = cadena -> 
        {                
            String titulo = cadena[0];
            String n = cadena[1];
            String s = cadena[2];
            String e = cadena[3];
            String o = cadena[4];

            Habitacion habitacion = this.habitaciones.get(titulo);
            Habitacion norte = this.habitaciones.get(n);
            Habitacion sur = this.habitaciones.get(s);
            Habitacion este = this.habitaciones.get(e);
            Habitacion oeste = this.habitaciones.get(o);

            habitacion.conectar(Direccion.NORTE, norte);
            habitacion.conectar(Direccion.SUR, sur);
            habitacion.conectar(Direccion.ESTE, este);
            habitacion.conectar(Direccion.OESTE, oeste);
        };
        
        Consumer<String[]> crearMonstruos = cadena -> 
        {                
            String tipo = cadena[0];
            String localizacion = cadena[1];
            
            Mundo mundo = Mundo.getInstancia();
            Monstruo monstruo = mundo.crearMonstruo(tipo);
            
            Habitacion habitacion = this.habitaciones.get(localizacion);
            habitacion.setMonstruo(monstruo);
        };
        
        Consumer<String[]> crearTesoros = cadena -> 
        {                
            String titulo = cadena[0];
            String descripcion = cadena[1];
            String localizacion = cadena[2];
            
            Tesoro tesoro = new Tesoro(titulo, descripcion);

            if (localizacion.contains("Inventario"))
            {
                Mundo mundo = Mundo.getInstancia();
                mundo.addInventario(tesoro);
            }
            else
            {
                Habitacion habitacion = this.habitaciones.get(localizacion);
                habitacion.getObjetos().add(tesoro);
            }
        };
                
        cargar(habitaciones, crearHabitacion);
        cargar(conexiones, crearConexiones);
        cargar(tesoros, crearTesoros);
        cargar(monstruos, crearMonstruos);
    }
    
    public final void cargar(String fichero, Consumer<String[]> funcion)
    {
        try
        {
            InputStream fstream = Recursos.cargarFichero(fichero);
            BufferedReader br = Recursos.leerFichero(fstream);
        
            String linea;
            while ((linea = br.readLine()) != null)
            {
                String[] cadena = linea.split(";");
                funcion.accept(cadena);
            }    

            Recursos.cerrarFichero(fstream);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Mapa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public HashMap<String, Habitacion> getHabitaciones()
    {
        return habitaciones;
    }
}
