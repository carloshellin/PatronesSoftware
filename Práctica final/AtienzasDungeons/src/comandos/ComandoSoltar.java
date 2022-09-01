package comandos;

import juego.Mundo;

public class ComandoSoltar extends ComandoJuego
{    

    public ComandoSoltar(String nombre) 
    {
        super(nombre);
    }
    
    @Override
    public void ejecutarJuego() 
    {
        Mundo.getInstancia().soltar(nombre.toLowerCase());
    }
}
