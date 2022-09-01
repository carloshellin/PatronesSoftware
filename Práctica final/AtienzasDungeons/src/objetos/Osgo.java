package objetos;

import atributos.Atributo;
import atributos.Bonificador;
import components.NullGraphicsComponente;
import juego.Dado;

public class Osgo extends Monstruo
{
    public Osgo()
    {
        super("Osgo", "Humanoide", "", new NullGraphicsComponente());
        
        fuerza = new Atributo(15);
        destreza = new Atributo(14);
        constitucion = new Atributo(13);
        inteligencia = new Atributo(8);
        sabiduria = new Atributo(11);
        carisma = new Atributo(9);
        
        fuerza.addBonificador(new Bonificador(2));
        destreza.addBonificador(new Bonificador(2));
        constitucion.addBonificador(new Bonificador(1));
        inteligencia.addBonificador(new Bonificador(-1));
        carisma.addBonificador(new Bonificador(-1));
        
        puntosGolpe = 27;
        claseArmadura = 16;
        bonificadorCompetencia = 2;
        
        objeto = "necronomicón";
    }

    @Override
    public int getAtributoAtaque() 
    {
        return fuerza.calcularValor();
    }

    @Override
    public int getDaño() 
    {
        Dado dado = new Dado(2, 8);
        return dado.tirar() + getAtributoAtaque();
    }
    
}
