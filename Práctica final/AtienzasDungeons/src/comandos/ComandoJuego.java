package comandos;

import juego.Mundo;

public abstract class ComandoJuego implements Comando
{
    protected final String nombre;
    
    public ComandoJuego(String nombre)
    {
        this.nombre = nombre;
    }
    
    public ComandoJuego()
    {
        this("");
    }
    
    @Override
    public void ejecutar()
    {
        Mundo mundo = Mundo.getInstancia();
        if (!mundo.getLocalizacion().getNombre().contains("Título"))
        {
            ejecutarJuego();
        }
    }
    
    public abstract void ejecutarJuego();
}
