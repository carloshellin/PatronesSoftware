package juego;

import java.util.Random;

public class Dado
{
    private final int numTiradas;
    private final int valorMax;

    public Dado(int numTiradas, int valorMax)
    {
        this.numTiradas = numTiradas;
        this.valorMax = valorMax;
    }
    
    public int tirar()
    {
        long seed = System.nanoTime();
        Random random = new Random(seed);
        
        return random.nextInt(numTiradas * valorMax) + numTiradas;
    }    
}
