package comandos;

import juego.Mundo;

public class ComandoAyuda extends ComandoJuego
{

    @Override
    public void ejecutarJuego() 
    {
        Mundo mundo = Mundo.getInstancia();
        mundo.mostrar("- “norte” o “n”, “sur” o “s”, “este” o “e” y"
                + " “oeste” u “o”: para moverse y orientarse se usan los"
                + " comandos con los cuatro puntos cardinales.");
        mundo.mostrar("- “mirar” o “m”: permite volver a conocer la "
                + "descripción de la cara de la habitación hacia la que se está "
                + "orientado. También los objetos o criaturas de la zona.");
        mundo.mostrar("- “inventario” o “i”: se mira lo que tenemos "
                + "en el inventario.");
        mundo.mostrar("- “info”: para consultar durante el juego los atributos"
                + " del personaje elegido.");
        mundo.mostrar("- “coger” o “c”: cuando en una determinada orientación "
                + "de una habitación aparezca en la descripción que hay"
                + " un objeto, puede añadirlo al inventario seguido del nombre"
                + " del objeto.");
        mundo.mostrar("- “usar” o “u”: seguido del nombre del objeto hace que puedas "
                + "ejecutar la acción que viene asociada con ese objeto.");
        mundo.mostrar("- “soltar” o “so”: para soltar un objeto se ejecuta el"
                + " comando seguido del nombre del objeto");
        mundo.mostrar("- “atacar” o “a”: simula un asalto hacia el rival según"
                + " las reglas de Dungeons and Dragons.");
        mundo.mostrar("- “esquivar”: es posible protegerse del ataque del "
                + "adversario.");
        mundo.mostrar("- “guardar”: en cualquier momento de la aventura se "
                + "puede usar para que realice un guardado del estado actual "
                + "del juego.");
        mundo.mostrar("- “ascender” y “descender”: Para subir o bajar en el "
                + "momento de escalado.");
        mundo.mostrar("** Se puede consultar los antiguos comandos pulsando "
                + "la flecha de arriba o abajo");
    }
    
}
