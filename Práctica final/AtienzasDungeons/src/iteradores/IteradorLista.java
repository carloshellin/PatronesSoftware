package iteradores;

import java.io.Serializable;
import java.util.ArrayList;

public class IteradorLista<T> implements Iterador, Serializable
{
    private final ArrayList<T> lista;
    private int cursor;
    
    public IteradorLista(ArrayList<T> lista)
    {
        this.lista = lista;
        this.cursor = lista.size();
    }
    
    public T actual()
    {
        return lista.get(cursor);
    }
    
    public boolean hayAnterior()
    {
        return 0 <= cursor - 1;
    }
    
    public T anterior()
    {
        cursor--;
        return actual();
    }
    
    @Override
    public boolean haySiguiente()
    {
        return cursor + 1 < lista.size();
    }

    @Override
    public T siguiente()
    {
        cursor++;
        return actual();
    }
}
