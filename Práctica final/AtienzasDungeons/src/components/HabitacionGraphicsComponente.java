package components;

import java.awt.Graphics;
import java.awt.Image;
import juego.Entidad;
import juego.Mundo;
import objetos.Habitacion;

public class HabitacionGraphicsComponente extends GraphicsComponente
{   
    public HabitacionGraphicsComponente(String fichero)
    {
        super(fichero);
    }
    
    @Override
    public void renderizar(Entidad obj, Graphics graphics)
    {
        Mundo mundo = Mundo.getInstancia();
        Habitacion habitacion = (Habitacion) obj;
        Image imagen = getImagen();
        
        if (mundo.getLocalizacion().getNombre().compareTo(habitacion.
                getNombre()) == 0)
        {
            graphics.drawImage(imagen, obj.getX() - (getAncho() / 2), 
                    obj.getY(), getAncho(), getAlto(), null);
        }
    }       
}
