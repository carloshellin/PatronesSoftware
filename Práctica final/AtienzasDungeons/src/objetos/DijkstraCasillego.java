package objetos;

import atributos.Atributo;
import atributos.Bonificador;
import components.NullGraphicsComponente;

public class DijkstraCasillego extends Personaje
{
    public DijkstraCasillego()
    {
        super("Dijkstra Casillego", "enano", "clérigo", 11,
                new NullGraphicsComponente());
       
        fuerza = new Atributo(14);
        destreza = new Atributo(9);
        constitucion = new Atributo(15);
        inteligencia = new Atributo(10);
        sabiduria = new Atributo(16);
        carisma = new Atributo(12);
        
        fuerza.addBonificador(new Bonificador(2));
        destreza.addBonificador(new Bonificador(-1));
        constitucion.addBonificador(new Bonificador(2));
        sabiduria.addBonificador(new Bonificador(1));
        carisma.addBonificador(new Bonificador(2));
        
        claseArmadura = 18;
        bonificadorCompetencia = 2;
    }

    @Override
    public int getAtributoAtaque()
    {
        return fuerza.calcularValor();
    }
}
