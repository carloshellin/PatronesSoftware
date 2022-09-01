package components;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;
import juego.Entidad;
import juego.Recursos;

public abstract class GraphicsComponente implements Serializable
{
    private int ancho;
    private int alto;
    private transient final Image imagen;
     
    public GraphicsComponente(String fichero)
    {
        this.imagen = Recursos.cargarImagen(fichero);
        this.ancho = imagen.getWidth(null);
        this.alto = imagen.getHeight(null);
    }
    
    public GraphicsComponente()
    {
        this.imagen = null;
        this.ancho = 0;
        this.alto = 0;
    }
    
    public abstract void renderizar(Entidad obj, Graphics graphics);

    public int getAncho() 
    {
        return ancho;
    }

    public void setAncho(int ancho) 
    {
        this.ancho = ancho;
    }

    public int getAlto() 
    {
        return alto;
    }

    public void setAlto(int alto) 
    {
        this.alto = alto;
    }
    
    public Image getImagen()
    {
        return imagen;
    }
}
