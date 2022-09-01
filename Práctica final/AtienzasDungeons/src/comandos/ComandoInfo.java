package comandos;

import juego.Mundo;

public class ComandoInfo extends ComandoJuego
{

    @Override
    public void ejecutarJuego() 
    {
        Mundo mundo = Mundo.getInstancia();
        mundo.mostrar(mundo.getJugadorDescripcion());
    }
    
}
