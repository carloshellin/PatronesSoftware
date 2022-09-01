package comandos;

import juego.Mundo;

public class ComandoNuevo extends ComandoTitulo
{
    @Override
    public void ejecutarTitulo()
    {
        Mundo.getInstancia().info();
    }
}
