package comandos;

import juego.Mundo;

public class ComandoUsar extends ComandoJuego
{

    public ComandoUsar(String nombre)
    {
        super(nombre);
    }
    
    @Override
    public void ejecutarJuego() 
    {
        Mundo.getInstancia().usar(nombre);
    }
    
}
