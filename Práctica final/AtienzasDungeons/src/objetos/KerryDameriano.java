package objetos;

import atributos.Atributo;
import atributos.Bonificador;
import components.NullGraphicsComponente;

public class KerryDameriano extends Personaje
{
    
    public KerryDameriano()
    {
        super("Kerry Dameriano", "humano", "guerrero", 12,
                new NullGraphicsComponente());
        
        fuerza = new Atributo(16);
        destreza = new Atributo(9);
        constitucion = new Atributo(15);
        inteligencia = new Atributo(11);
        sabiduria = new Atributo(13);
        carisma = new Atributo(14);
        
        fuerza.addBonificador(new Bonificador(3));
        destreza.addBonificador(new Bonificador(-1));
        constitucion.addBonificador(new Bonificador(2));
        sabiduria.addBonificador(new Bonificador(1));
        carisma.addBonificador(new Bonificador(2));
        
        claseArmadura = 17;
        bonificadorCompetencia = 2;
    }    

    @Override
    public int getAtributoAtaque()
    {
        return fuerza.calcularValor();
    }  
}