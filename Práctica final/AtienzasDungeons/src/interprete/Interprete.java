package interprete;

import comandos.Comando;
import comandos.ComandoAscender;
import comandos.ComandoAtacar;
import comandos.ComandoAyuda;
import comandos.ComandoCargar;
import comandos.ComandoCoger;
import comandos.ComandoDescender;
import comandos.ComandoEsquivar;
import comandos.ComandoGuardar;
import comandos.ComandoInfo;
import comandos.ComandoInventario;
import comandos.ComandoSoltar;
import comandos.ComandoMirar;
import comandos.ComandoMover;
import comandos.ComandoNuevo;
import comandos.ComandoElegir;
import comandos.ComandoUsar;
import java.util.ArrayList;
import java.util.StringTokenizer;
import juego.Direccion;
import juego.Mundo;

public class Interprete
{
    private final Contexto contexto;
    private Comando comando;
    private final Mundo mundo;
    
    public Interprete(Contexto contexto)
    {
        this.contexto = contexto;
        this.mundo = Mundo.getInstancia();
    }
    
    private ArrayList<String> parser(String expresion)
    {
        String tokenActual;
        String delimitadores = " \t,.:;?!\"'";
        StringTokenizer tokens = new StringTokenizer(expresion, delimitadores);
        ArrayList<String> palabras = new ArrayList<>();

        while (tokens.hasMoreTokens())
        {
            tokenActual = tokens.nextToken();
            palabras.add(tokenActual);
        }
        
        return palabras;
    }
    
    private void procesarVerbo(String verbo)
    {        
        if (!contexto.getComandos().contains(verbo))
        {
            mundo.mostrar(verbo + " no es un verbo conocido.");
        }
        else
        {
            switch (verbo)
            {
                case "norte":
                case "n":
                {
                    comando = new ComandoMover(Direccion.NORTE);
                    comando.ejecutar();
                } break;
                
                case "sur":
                case "s":
                {
                    comando = new ComandoMover(Direccion.SUR);
                    comando.ejecutar();
                } break;
                
                case "este":
                case "e":
                {
                    comando = new ComandoMover(Direccion.ESTE);
                    comando.ejecutar();
                } break;
                
                case "oeste":
                case "o":
                {
                    comando = new ComandoMover(Direccion.OESTE);
                    comando.ejecutar();
                } break;
                
                case "mirar":
                case "m":
                {
                    comando = new ComandoMirar();
                    comando.ejecutar();
                } break;
                
                case "coger":
                case "c":
                {
                    comando = new ComandoCoger("");
                    comando.ejecutar();
                } break;
                
                case "soltar":
                case "so":
                {
                    comando = new ComandoSoltar("");
                    comando.ejecutar();
                } break;
                
                case "inventario":
                case "i":
                {
                    comando = new ComandoInventario();
                    comando.ejecutar();
                } break;
                
                case "guardar":
                {
                    comando = new ComandoGuardar();
                    comando.ejecutar();
                } break;
                
                case "cargar":
                {
                    comando = new ComandoCargar();
                    comando.ejecutar();
                } break;
                
                case "nuevo":
                {
                    comando = new ComandoNuevo();
                    comando.ejecutar();
                } break;        
                
                case "atacar":
                case "a":
                {
                    comando = new ComandoAtacar();
                    comando.ejecutar();
                } break;     
                
                case "esquivar":
                {
                    comando = new ComandoEsquivar();
                    comando.ejecutar();
                } break;
                
                case "info":
                {
                    comando = new ComandoInfo();
                    comando.ejecutar();
                } break;
                
                case "elegir":
                {
                    comando = new ComandoElegir("");
                    comando.ejecutar();
                } break;
                
                case "usar":
                case "u":
                {
                    comando = new ComandoUsar("");
                    comando.ejecutar();
                } break;
                
                case "ayuda":
                {
                    comando = new ComandoAyuda();
                    comando.ejecutar();
                } break;
                
                case "ascender":
                {
                    comando = new ComandoAscender();
                    comando.ejecutar();
                } break;
                
                case "descender":
                {
                    comando = new ComandoDescender();
                    comando.ejecutar();
                } break;
            }
        }
    }
    
    private void procesarVerboNombre(String verbo, String nombre)
    {       
        if (!contexto.getComandos().contains(verbo))
        {
            mundo.mostrar(verbo + " no es un verbo conocido.");
        }
        else if (!contexto.getObjetos().contains(nombre.toLowerCase()) 
                && !verbo.contains("elegir"))
        {
            mundo.mostrar(nombre + " no es un objeto conocido.");
        }
        else
        {
            switch (verbo)
            {
                case "coger":
                case "c":
                {
                    comando = new ComandoCoger(nombre);
                    comando.ejecutar();
                } break;
                
                case "soltar":
                case "s":
                {
                    comando = new ComandoSoltar(nombre);
                    comando.ejecutar();
                } break;
                              
                case "elegir":
                {
                    comando = new ComandoElegir(nombre);
                    comando.ejecutar();
                } break;
                
                case "usar":
                case "u":
                {
                    comando = new ComandoUsar(nombre);
                    comando.ejecutar();
                } break;
            }
        }
    }
        
    public void interpretar(String expresion)
    {        
        ArrayList<String> palabras = parser(expresion);
        
        if (palabras.isEmpty())
        {
        }
        else if (palabras.size() == 1)
        {
            procesarVerbo(palabras.get(0));
        }
        else if (palabras.size() == 2)
        {
            procesarVerboNombre(palabras.get(0), palabras.get(1));
        }
        else
        {
            mundo.mostrar("Solo 2 palabras como comando está permitido.");
        }
    }
}
