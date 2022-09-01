package comandos;

import juego.Mundo;

public abstract class ComandoTitulo implements Comando
{    
    protected final String nombre;
    
    public ComandoTitulo(String nombre)
    {
        this.nombre = nombre;
    }
    
    public ComandoTitulo()
    {
        this("");
    }
    
    @Override
    public void ejecutar()
    {
        Mundo mundo = Mundo.getInstancia();
        if (mundo.getLocalizacion().getNombre().contains("Título"))
        {
            ejecutarTitulo();
        }
    }
    
    public abstract void ejecutarTitulo();
}
