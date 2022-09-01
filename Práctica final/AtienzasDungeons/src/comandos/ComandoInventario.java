package comandos;

import juego.Mundo;

public class ComandoInventario extends ComandoJuego
{
    @Override
    public void ejecutarJuego()
    {
        Mundo.getInstancia().inventario();
    }
}
