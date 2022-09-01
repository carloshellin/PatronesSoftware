package juego;

import objetos.DijkstraCasillego;
import objetos.Goblin;
import objetos.KerryDameriano;
import objetos.Lobo;
import objetos.Monstruo;
import objetos.Osgo;
import objetos.Personaje;

public class Factoria
{
    public enum TipoPersonaje
    {
        Dijkstra,
        Kerry
    }
    
    public enum TipoEnemigo
    {
        Lobo,
        Goblin,
        Osgo
    }
    
    public Personaje getPersonaje(TipoPersonaje tipo)
    {
        switch (tipo)
        {
            case Dijkstra:
            {
                return new DijkstraCasillego();
            }
            
            case Kerry:
            {
                return new KerryDameriano();
            }
        }
        
        return null;
    }
    
    public Monstruo getEnemigo(TipoEnemigo tipo)
    {
        switch (tipo)
        {
            case Lobo:
            {
                return new Lobo();
            }
            
            case Goblin:
            {
                return new Goblin();
            }
            
            case Osgo:
            {
                return new Osgo();
            }
        }
        
        return null;
    }
}
