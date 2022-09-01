package comandos;

import juego.Mundo;

public class ComandoAscender extends ComandoJuego
{

    @Override
    public void ejecutarJuego() 
    {
        Mundo.getInstancia().escalar("ascender");
    }
    
}
