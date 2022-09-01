package interprete;

import java.util.ArrayList;
import java.util.Arrays;

public class Contexto
{
    private final ArrayList<String> comandos;
    private final ArrayList<String> objetos;
    
    public Contexto()
    {
        comandos = new ArrayList<>(Arrays.asList("norte", "n", "sur", "s", 
                "este", "e", "oeste", "o", "mirar", "m", "coger", "c", 
                "soltar", "so", "inventario", "i", "guardar", "cargar", "nuevo",
                "elegir", "atacar", "a", "esquivar", "info", "usar", "u", "ayuda",
                "ascender", "descender"));
        objetos = new ArrayList<>(Arrays.asList("espada", "antorcha",
                "llave", "poción", "necronomicón"));
    }

    public ArrayList<String> getComandos()
    {
        return comandos;
    }

    public ArrayList<String> getObjetos()
    {
        return objetos;
    }
}
