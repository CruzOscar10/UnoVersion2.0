package Uno2_0;

import java.util.Scanner;

public class Game {

    private Deck deck;
    private Hand jugador;
    private Hand computadora;
    private Card cartaMesa;

    private Scanner scanner;

    private boolean jugadorDijoUNO=false;

    private int penalizacion=0;
    private boolean salto=false;

    public Game(){

        deck=new Deck();
        jugador=new Hand();
        computadora=new Hand();
        scanner=new Scanner(System.in);

    }

    public void iniciar(){

        System.out.println("===== INICIA EL JUEGO UNO =====");

        repartir();

        do{
            cartaMesa=deck.robarCarta();
        }while(cartaMesa.getTipo()!=Card.Tipo.NUMERO);

        System.out.println("Carta inicial en mesa: "+cartaMesa);

        while(true){

            turnoJugador();

            verificarUNOJugador();

            if(jugador.estaVacia()){
                System.out.println("===== GANASTE EL JUEGO =====");
                break;
            }

            turnoComputadora();

            if(computadora.estaVacia()){
                System.out.println("===== LA COMPUTADORA GANA =====");
                break;
            }

        }

    }

    private void repartir(){

        for(int i=0;i<7;i++){

            jugador.agregarCarta(deck.robarCarta());
            computadora.agregarCarta(deck.robarCarta());

        }

    }

    private void turnoJugador(){

        if(penalizacion>0){

            System.out.println("Debes robar "+penalizacion+" cartas");

            for(int i=0;i<penalizacion;i++)
                jugador.agregarCarta(deck.robarCarta());

            penalizacion=0;
            return;

        }

        if(salto){

            System.out.println("Tu turno fue saltado");
            salto=false;
            return;

        }

        boolean roboCarta=false;

        System.out.println("\n----- TU TURNO -----");
        System.out.println("Carta en mesa: "+cartaMesa);

        while(true){

            jugador.mostrarMano();

            System.out.println("Elige carta o escribe -1 para robar:");

            int op;

            if(scanner.hasNextInt())
                op=scanner.nextInt();
            else{
                scanner.next();
                continue;
            }

            if(op==-1){

                if(roboCarta){
                    System.out.println("Ya robaste una vez este turno.");
                    continue;
                }

                roboCarta=true;

                Card robada=deck.robarCarta();

                System.out.println("Robaste: "+robada);

                jugador.agregarCarta(robada);

                if(!robada.esJugableSobre(cartaMesa)){

                    System.out.println("La carta no es jugable. Pierdes turno.");
                    return;

                }else{

                    System.out.println("La carta es jugable. Puedes usarla.");
                    continue;
                }

            }

            if(op<0 || op>=jugador.size()){
                System.out.println("Indice invalido.");
                continue;
            }

            Card c=jugador.getCarta(op);

            if(!c.esJugableSobre(cartaMesa)){
                System.out.println("Carta no valida.");
                continue;
            }

            jugador.jugarCarta(op);

            System.out.println("Jugaste: "+c);

            cartaMesa=c;

            aplicarEfecto(c,true);

            if(jugador.size()==1){

                System.out.println("¡Te queda una carta! ¿Deseas decir UNO? (s/n)");

                String r=scanner.next();

                if(r.equalsIgnoreCase("s")){
                    jugadorDijoUNO=true;
                    System.out.println("Jugador dice UNO!");
                }

            }

            break;

        }

    }

    private void turnoComputadora(){

        System.out.println("\n----- TURNO DE LA COMPUTADORA -----");

        if(penalizacion>0){

            System.out.println("Computadora roba "+penalizacion+" cartas");

            for(int i=0;i<penalizacion;i++)
                computadora.agregarCarta(deck.robarCarta());

            penalizacion=0;
            return;

        }

        if(salto){

            System.out.println("Turno de computadora saltado");
            salto=false;
            return;

        }

        for(int i=0;i<computadora.size();i++){

            Card c=computadora.getCarta(i);

            if(c.esJugableSobre(cartaMesa)){

                computadora.jugarCarta(i);

                System.out.println("Computadora juega: "+c);

                cartaMesa=c;

                aplicarEfecto(c,false);

                // LOGICA UNO COMPUTADORA
                if(computadora.size()==1){

                    if(Math.random()<0.8){

                        System.out.println("Computadora dice UNO!");

                    }else{

                        System.out.println("Computadora olvidó decir UNO!");
                        System.out.println("Penalización: roba 2 cartas.");

                        computadora.agregarCarta(deck.robarCarta());
                        computadora.agregarCarta(deck.robarCarta());

                    }

                }

                return;

            }

        }

        System.out.println("Computadora no tiene jugada válida.");
        System.out.println("Computadora roba una carta.");

        computadora.agregarCarta(deck.robarCarta());

    }

    private void aplicarEfecto(Card c, boolean jugador){

        switch(c.getTipo()){

            case SALTO:

                System.out.println("Carta SALTO jugada.");
                salto=true;

                break;

            case REVERSA:

                System.out.println("Carta REVERSA jugada.");
                salto=true;

                break;

            case ROBA2:

                System.out.println("Carta ROBA 2 jugada.");
                penalizacion=2;

                break;

            case ROBA4:

                System.out.println("Carta ROBA 4 jugada.");
                penalizacion=4;

                cambiarColor(c,jugador);

                break;

            case COMODIN:

                System.out.println("Carta COMODIN jugada.");

                cambiarColor(c,jugador);

                break;

            default:
                break;

        }

    }

    private void cambiarColor(Card c, boolean jugador){

        if(jugador){

            System.out.println("Elige nuevo color (rojo azul verde amarillo)");

            String color=scanner.next().toLowerCase();

            c.setColor(color);

            System.out.println("Nuevo color seleccionado: "+color);

        }

        else{

            String[] colores={"rojo","azul","verde","amarillo"};

            int r=(int)(Math.random()*4);

            c.setColor(colores[r]);

            System.out.println("Computadora cambia el color a: "+colores[r]);

        }

    }

    private void verificarUNOJugador(){

        if(jugador.size()==1 && !jugadorDijoUNO){

            System.out.println("No dijiste UNO.");
            System.out.println("Penalización: robas 2 cartas.");

            jugador.agregarCarta(deck.robarCarta());
            jugador.agregarCarta(deck.robarCarta());

        }

        jugadorDijoUNO=false;

    }

}