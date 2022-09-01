package atributos;

import java.io.Serializable;

public class AtributoBase implements Serializable
{
    private final int valorBase;
    
    public AtributoBase(int valorBase)
    {
        this.valorBase = valorBase;
    }
    
    public int getValorBase()
    {
        return valorBase;
    }
}
