package comandos;

import juego.Mundo;

public class ComandoCoger extends ComandoJuego
{

    public ComandoCoger(String nombre) 
    {
        super(nombre);
    }
    
    @Override
    public void ejecutarJuego()
    {
        Mundo.getInstancia().coger(nombre.toLowerCase());
    }
}
