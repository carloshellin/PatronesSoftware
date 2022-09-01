package objetos;

import components.GraphicsComponente;
import components.NullGraphicsComponente;
import juego.Entidad;

public class Escenario extends Entidad
{
    private String nombre;
    private String descripcion;
    
    public Escenario(String nombre, String descripcion,
            GraphicsComponente graphicsComponente)
    {
        super(graphicsComponente);
        
        this.nombre = nombre;
        
        String[] lineas = descripcion.split("\\\\n");
        if (lineas.length > 1)
        {
            this.descripcion = "";
            for (String linea : lineas)
            {
                this.descripcion += linea + System.lineSeparator();
            }
        }
        else
        {
            this.descripcion = descripcion;
        }
    }
    
    public Escenario(String nombre, String descripcion)
    {
        this(nombre, descripcion, new NullGraphicsComponente());
    }
    
    public String getNombre()
    {
        return nombre;
    }

    public String getDescripcion()
    {
        return descripcion;
    }
}
