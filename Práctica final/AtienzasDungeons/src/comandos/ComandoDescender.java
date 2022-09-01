package comandos;

import juego.Mundo;

public class ComandoDescender extends ComandoJuego
{

    @Override
    public void ejecutarJuego() 
    {
        Mundo.getInstancia().escalar("descender");
    }
    
}
