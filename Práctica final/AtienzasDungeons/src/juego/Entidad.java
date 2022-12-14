package juego;

import components.GraphicsComponente;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Entidad implements Serializable
{    
    private int x;
    private int y;
    private int ancho;
    private int alto;
    
    private final GraphicsComponente graphicsComponente;
    
    public Entidad(GraphicsComponente graphicsComponente)
    {
        this.graphicsComponente = graphicsComponente;
    }
    
    public void renderizar(Graphics graphics)
    {
        graphicsComponente.renderizar(this, graphics);
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

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
}
