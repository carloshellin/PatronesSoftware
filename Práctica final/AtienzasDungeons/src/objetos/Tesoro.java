package objetos;

import java.io.Serializable;

public class Tesoro extends Escenario implements Serializable
{
    public Tesoro(String nombre, String descripcion)
    {
        super(nombre, descripcion);
    }
}