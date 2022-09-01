package juego;

public class Main
{
    public static void main(String[] args)
    {
        Mundo mundo = Mundo.getInstancia();
        mundo.iniciar();
        mundo.bucleJuego();
    }    
}
