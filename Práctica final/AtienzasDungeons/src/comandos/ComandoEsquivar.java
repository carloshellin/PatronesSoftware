package comandos;

import juego.Mundo;

public class ComandoEsquivar extends ComandoJuego
{
    @Override
    public void ejecutarJuego() 
    {
        Mundo.getInstancia().esquivar();
    }   
}
