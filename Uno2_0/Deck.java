package Uno2_0;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cartas;

    public Deck(){

        cartas = new ArrayList<>();

        String[] colores={"rojo","azul","verde","amarillo"};

        for(String c: colores){

            for(int i=0;i<=9;i++)
                cartas.add(new Card(c,i));

            cartas.add(new Card(c,Card.Tipo.SALTO));
            cartas.add(new Card(c,Card.Tipo.REVERSA));
            cartas.add(new Card(c,Card.Tipo.ROBA2));
        }

        for(int i=0;i<4;i++){
            cartas.add(new Card("negro",Card.Tipo.COMODIN));
            cartas.add(new Card("negro",Card.Tipo.ROBA4));
        }

        Collections.shuffle(cartas);

    }
    public Card robarCarta(){

        if(cartas.isEmpty())
            return null;

        return cartas.remove(0);
    }
}