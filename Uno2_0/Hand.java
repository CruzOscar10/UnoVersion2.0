package Uno2_0;

import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> cartas;

    public Hand(){
        cartas=new ArrayList<>();
    }

    public void agregarCarta(Card c){
        cartas.add(c);
    }

    public Card jugarCarta(int i){
        return cartas.remove(i);
    }

    public Card getCarta(int i){
        return cartas.get(i);
    }

    public int size(){
        return cartas.size();
    }

    public boolean estaVacia(){
        return cartas.isEmpty();
    }

    public void mostrarMano(){

        System.out.println("Tus cartas:");

        for(int i=0;i<cartas.size();i++)
            System.out.println(i+": "+cartas.get(i));

    }

}