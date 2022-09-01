package objetos;

import atributos.Atributo;
import atributos.Bonificador;
import components.NullGraphicsComponente;
import juego.Dado;

public class Goblin extends Monstruo
{

    public Goblin()
    {
        super("Goblin", "humanoide", "", new NullGraphicsComponente());
        
        fuerza = new Atributo(8);
        destreza = new Atributo(14);
        constitucion = new Atributo(10);
        inteligencia = new Atributo(10);
        sabiduria = new Atributo(8);
        carisma = new Atributo(8);
        
        fuerza.addBonificador(new Bonificador(-1));
        destreza.addBonificador(new Bonificador(2));
        sabiduria.addBonificador(new Bonificador(-1));
        carisma.addBonificador(new Bonificador(-1));
        
        puntosGolpe = 20;
        claseArmadura = 15;
        bonificadorCompetencia = 2;
        
        objeto = "llave";
    }

    @Override
    public int getAtributoAtaque()
    {
        return destreza.calcularValor();
    }    

    @Override
    public int getDaño()
    {
        Dado dado = new Dado(1, 6);
        return dado.tirar() + getAtributoAtaque();
    }
}
