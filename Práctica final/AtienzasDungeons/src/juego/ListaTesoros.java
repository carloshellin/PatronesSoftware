package juego;

import java.util.ArrayList;
import objetos.Tesoro;

public class ListaTesoros extends ArrayList<Tesoro>
{
    public String tesoros() 
    {
        String cadena = "";
        
        for (Tesoro tesoro : this)
        {
            cadena += "- " + tesoro.getNombre() + ": " +
                    tesoro.getDescripcion() + "\n";
        }
        
        return cadena;
    }
    
    public String describir()
    {
        String cadena = "Ves los siguientes objetos en la zona:\n";
        
        if (isEmpty())
        {
            cadena = "No hay objetos en la zona.";
        }
        else
        {
            cadena += tesoros();
        }
        
        return cadena;
    }
    
    public Tesoro get(String nombre)
    {
        for (Tesoro tesoro : this)
        {
            if (tesoro.getNombre().toLowerCase().equals(nombre))
            {
                return tesoro;
            }
        }
        
        return null;
    }
}
