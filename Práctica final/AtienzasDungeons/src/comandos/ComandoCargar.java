package comandos;

import juego.Mundo;

public class ComandoCargar implements Comando
{
    @Override
    public void ejecutar()
    {
        Mundo.getInstancia().cargar();
    }   
}
