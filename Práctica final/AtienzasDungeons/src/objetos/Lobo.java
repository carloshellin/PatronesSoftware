package objetos;

import atributos.Atributo;
import atributos.Bonificador;
import components.NullGraphicsComponente;
import juego.Dado;

public class Lobo extends Monstruo
{
    
    public Lobo()
    {
        super("Lobo", "Bestia", "", new NullGraphicsComponente());
        
        fuerza = new Atributo(12);
        destreza = new Atributo(15);
        constitucion = new Atributo(12);
        inteligencia = new Atributo(3);
        sabiduria = new Atributo(12);
        carisma = new Atributo(6);
        
        fuerza.addBonificador(new Bonificador(1));
        destreza.addBonificador(new Bonificador(2));
        constitucion.addBonificador(new Bonificador(1));
        inteligencia.addBonificador(new Bonificador(-4));
        sabiduria.addBonificador(new Bonificador(1));
        carisma.addBonificador(new Bonificador(-2));
        
        puntosGolpe = 11;
        claseArmadura = 13;
        bonificadorCompetencia = 2;
        objeto = "poción";
    }
    
    @Override
    public int getAtributoAtaque()
    {
        return destreza.calcularValor();
    }

    @Override
    public int getDaño() 
    {
        Dado dado = new Dado(2, 4);
        return dado.tirar() + getAtributoAtaque();
    }   
}
