package comandos;

import juego.Mundo;

public class ComandoAtacar extends ComandoJuego
{
    @Override
    public void ejecutarJuego()
    {
        Mundo.getInstancia().atacar();
    }
}
