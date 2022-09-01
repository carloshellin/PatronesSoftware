package components;

import java.awt.Graphics;
import java.awt.Image;
import juego.Entidad;

public class SpriteGraphicsComponente extends GraphicsComponente
{

    public SpriteGraphicsComponente(String fichero)
    {
        super(fichero);
    }

    @Override
    public void renderizar(Entidad obj, Graphics graphics)
    {
        Image imagen = getImagen();
        
        graphics.drawImage(imagen, obj.getX(), obj.getY(), getAncho(), 
                getAlto(), null);
    }    
}
