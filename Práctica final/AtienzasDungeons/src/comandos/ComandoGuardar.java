package comandos;

import juego.Mundo;

public class ComandoGuardar extends ComandoJuego
{
    @Override
    public void ejecutarJuego()
    {
        Mundo.getInstancia().guardar();
    }   
}
