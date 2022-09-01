package juego;

public enum Direccion
{
    NORTE,
    SUR,
    ESTE,
    OESTE;
    
    public static Direccion contrario(Direccion direccion)
    {
        switch (direccion)
        {
            case NORTE:
            {
                return SUR;
            }
            
            case SUR:
            {
                return NORTE;
            }
            
            case ESTE:
            {
                return OESTE;
            }
            
            case OESTE:
            {
                return ESTE;
            }
        }
        
        return null;
    }
};