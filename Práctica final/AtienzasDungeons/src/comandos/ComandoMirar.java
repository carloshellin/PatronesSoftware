package comandos;

import juego.Mundo;

public class ComandoMirar extends ComandoJuego
{
    @Override
    public void ejecutarJuego()
    {
        Mundo.getInstancia().mirar();
    }
}
