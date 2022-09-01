package atributos;

import java.util.ArrayList;

public class Atributo extends AtributoBase
{
    private final ArrayList<Bonificador> bonificadores;
    
    public Atributo(int valorInicial)
    {
        super(valorInicial);
        
        this.bonificadores = new ArrayList<>();
    }
    
    public void addBonificador(Bonificador bonificador)
    {
        bonificadores.add(bonificador);
    }
    
    public void removeBonificador(Bonificador bonificador)
    {
        bonificadores.remove(bonificador);
    }
    
    public int calcularValor()
    {      
        int valorBonificador = 0;
        
        for (Bonificador bonificador : bonificadores)
        {
            valorBonificador += bonificador.getValorBase();
        }
        
        return valorBonificador;
    }
    
    @Override
    public String toString()
    {
        String string = Integer.toString(getValorBase());
        int valor = calcularValor();
        
        string += " (";
        if (valor >= 0)
        {
             string += "+";
        }
        string += Integer.toString(calcularValor()) + ")";    
        
        return string;
    }    
}
