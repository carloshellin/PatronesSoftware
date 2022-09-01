package interfaz;

import java.awt.Insets;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Ventana extends JFrame
{   
    private final int ancho;
    private final int alto;
    private final String titulo;
    private final transient BufferedImage[] buffers;
    private transient BufferedImage bufferActual;
    private transient BufferedImage bufferSiguiente;
    private int bordeX;
    private int bordeY;
    private final JTextField entrada;
    private final JTextArea salida;
    
    public Ventana(String titulo, int ancho, int alto)
    {       
        this.ancho = ancho;
        this.alto = alto;
        this.titulo = titulo;
        
        buffers = new BufferedImage[2];
        
        iniciarVentana();
        
        entrada = new JTextField();
        salida = new JTextArea();
        
        iniciarContenido();
    }
    
    private void iniciarVentana()
    {
        setTitle(titulo);
        setSize(ancho, alto);
        setLayout(null);
        setBounds(0, 0, ancho, alto);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
        setVisible(true);
        
        Insets bordeInterior = getInsets();
        
        bordeX = bordeInterior.left + bordeInterior.right;
        bordeY = bordeInterior.top + bordeInterior.bottom;
        
        setVisible(false);
    }
    
    private void iniciarContenido()
    {
        entrada.setSize(ancho - bordeX , alto / 36);
        entrada.setBounds(0, alto - (bordeY + entrada.getHeight()),
                ancho - bordeX, entrada.getHeight());
        
        salida.setLineWrap(true);
        salida.setEditable(false);
        JScrollPane scroll = new JScrollPane(salida,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBounds(0, (alto * 2) / 3, ancho - bordeX, alto / 4);
        
        add(scroll);
        add(entrada);
        
        buffers[0] = new BufferedImage(ancho - bordeX,
                alto - (bordeY + entrada.getHeight() + scroll.getHeight()),
                TYPE_INT_ARGB);
        buffers[1] = new BufferedImage(ancho - bordeX,
                alto - (bordeY + entrada.getHeight() + scroll.getHeight()),
                TYPE_INT_ARGB);
        
        bufferActual = buffers[0];
        bufferSiguiente = buffers[1];
        
        setVisible(true);
        
        entrada.requestFocus();
    }
    
    public void intercambiar()
    {
        BufferedImage temporal = bufferActual;
        bufferActual = bufferSiguiente;
        bufferSiguiente = temporal;
    }

    public int getAncho()
    {
        return ancho;
    }

    public int getAlto()
    {
        return alto;
    }

    public BufferedImage getBuffer()
    {
        return bufferActual;
    }

    public JTextField getEntrada()
    {
        return entrada;
    }

    public JTextArea getSalida()
    {
        return salida;
    }
}
