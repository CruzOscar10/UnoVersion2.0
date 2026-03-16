package Uno2_0;

public class Card {

    public enum Tipo {
        NUMERO, SALTO, REVERSA, ROBA2, COMODIN, ROBA4
    }

    private String color;
    private Tipo tipo;
    private int numero;

    public Card(String color, int numero){
        this.color=color;
        this.numero=numero;
        this.tipo=Tipo.NUMERO;
    }

    public Card(String color, Tipo tipo){
        this.color=color;
        this.tipo=tipo;
    }

    public String getColor(){
        return color;
    }

    public Tipo getTipo(){
        return tipo;
    }

    public int getNumero(){
        return numero;
    }

    public void setColor(String color){
        this.color=color;
    }

    public boolean esJugableSobre(Card otra){

        if(tipo==Tipo.COMODIN || tipo==Tipo.ROBA4)
            return true;

        if(color.equals(otra.color))
            return true;

        if(tipo==Tipo.NUMERO && otra.tipo==Tipo.NUMERO && numero==otra.numero)
            return true;

        if(tipo==otra.tipo && tipo!=Tipo.NUMERO)
            return true;

        return false;
    }

    public String toString(){

        if(tipo==Tipo.NUMERO)
            return color+" "+numero;

        return color+" "+tipo;
    }

}