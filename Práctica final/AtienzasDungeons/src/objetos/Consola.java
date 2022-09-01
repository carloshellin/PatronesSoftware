package objetos;

import components.NullGraphicsComponente;
import interprete.Contexto;
import interprete.Interprete;
import iteradores.IteradorLista;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import juego.Entidad;
import juego.Mundo;

public class Consola extends Entidad implements Serializable
{
    private JTextField entrada;
    private final JTextArea salida;
    private final ArrayList<String> historial;
    private IteradorLista<String> iterador;
    
    public Consola()
    {
        super(new NullGraphicsComponente());
        
        historial = new ArrayList<>();
        
        Mundo mundo = Mundo.getInstancia();
        
        this.entrada = mundo.getEntrada();
        this.salida =  mundo.getSalida();
        
        Interprete interprete = new Interprete(new Contexto());
        
        entrada.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_ENTER:
                    {
                        String expresion = entrada.getText();
                        
                        mostrar("> " + expresion);
                        entrada.setText("");

                        if (expresion.length() > 1)
                        {
                            historial.add(expresion);    
                        }
                        
                        iterador = new IteradorLista<>(historial);

                        interprete.interpretar(expresion);
                    } break;
                    
                    case KeyEvent.VK_UP:
                    {                        
                        if (iterador != null && iterador.hayAnterior())
                        {
                            entrada.setText(iterador.anterior());
                        }
                    } break;
                    
                    case KeyEvent.VK_DOWN:
                    {
                        if (iterador != null && iterador.haySiguiente())
                        {
                            entrada.setText(iterador.siguiente());
                        }
                    } break;
                }
            }
        });
    }
    
    public void mostrar(String texto)
    {
        salida.append(texto + "\n");
        salida.setCaretPosition(salida.getDocument().getLength());
    }
}
