package objetos;

import components.SpriteGraphicsComponente;
import juego.Entidad;

public class Fondo extends Entidad
{
    public Fondo()
    {
        super(new SpriteGraphicsComponente("fondo.jpg"));
    }
}
