package comandos;

import juego.Mundo;

public class ComandoElegir extends ComandoTitulo
{    

    public ComandoElegir(String nombre) 
    {
        super(nombre);
    }
    
    @Override
    public void ejecutarTitulo()
    {
        Mundo mundo = Mundo.getInstancia();
        int numero;
        try
        {
            numero = Integer.parseInt(nombre);
            
        }
        catch (final NumberFormatException e)
        {
            mundo.mostrar("Debes de introducir un número.");
            return;
        }
        
        mundo.elegir(numero);
    }
    
}
