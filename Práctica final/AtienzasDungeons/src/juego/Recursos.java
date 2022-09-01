package juego;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Recursos
{
    private static final String CARPETA = "/recursos/";
    private static Clip clip = null;
    
    public static Image cargarImagen(String fichero)
    {
        try 
        {
            return ImageIO.read(Recursos.class.getResourceAsStream(CARPETA + fichero));
        }
        catch (IOException ex) 
        {
            Logger.getLogger(Recursos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public static InputStream cargarFichero(String fichero)
    {
        return Recursos.class.getResourceAsStream(CARPETA + fichero);
    }
    
    public static FileInputStream leerPartida(String fichero)
    {
        try
        {
            return new FileInputStream(fichero);
        }
        catch (FileNotFoundException ex)
        {
        }
        
        return null;
    }
    
    public static FileOutputStream escribirPartida(String fichero)
    {
        try
        {
            return new FileOutputStream(fichero);
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(Recursos.class.getName()).log(Level.SEVERE, null, 
                    ex);
        }
        
        return null;
    }
    
    public static BufferedReader leerFichero(InputStream fstream)
    {
        return new BufferedReader(new InputStreamReader(fstream));
    }
    
    public static void cerrarFichero(InputStream fstream)
    {
        try
        {
            fstream.close();
        }
        catch (IOException ex)
        {
            Logger.getLogger(Recursos.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }
    
    public static void reproducir(String nombre) 
    {
        try 
        {
            if (clip == null) clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                     new BufferedInputStream(Recursos.class.
                            getResourceAsStream(CARPETA + nombre + ".wav")));
            clip.open(inputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } 
        catch (UnsupportedAudioFileException | IOException | 
                LineUnavailableException ex)
        {
            Logger.getLogger(Recursos.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }
    
    public static void stop() 
    {
        if (clip != null) 
        {
            clip.stop();
            clip.close();
        }
    }
}
