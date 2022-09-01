package comandos;

import juego.Direccion;
import juego.Mundo;
import objetos.Habitacion;

public class ComandoMover extends ComandoJuego
{
    private final Direccion direccion;
    
    public ComandoMover(Direccion direccion)
    {
        this.direccion = direccion;
    }
    
    @Override
    public void ejecutarJuego()
    {
        Mundo mundo = Mundo.getInstancia();
        
        Habitacion localizacion = mundo.getLocalizacion();
        Habitacion siguiente = localizacion.getHabitacion(direccion);
        
        if (siguiente.getNombre().compareTo("SinSalida") == 0)
        {
            mundo.mostrar(siguiente.getDescripcion());
        }
        else
        {
            mundo.mover(direccion);
        }
    }
}
