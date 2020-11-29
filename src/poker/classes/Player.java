package poker.classes;

import java.util.Scanner;

public class Player {

    static int numPlayers =0;
    private final Card[] hand = {new Card(), new Card(), new Card(), new Card(), new Card()};
    private final int numPlayer;

    Player() {
        numPlayers++;
        this.numPlayer= numPlayers;
    }

    int getNumJoueur(){
        return  this.numPlayer;
    }

    Card[]getHand() {
        return hand;
    }


    static boolean duplicatesallcard(Card[] j1, Card[] j2){
        for (int i=0; i< 5;i++){

            for(int j=0;j< 5;j++){

                if ( (j1[i].getValCarte())==(j2[j].getValCarte()) && (j1[i].getIndexCouleur())==(j2[j].getIndexCouleur()) ) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean duplicates(String[] totest){
        for (int i = 0; i < 5; i++) {
            for (int j=0;j<5;j++) {

                if ( (totest[i].equals(totest[j])) && (i!=j)  ) {
                    return true;
                }
            }
        }
        return false;
    }




    public String[] entry(){
        final int NUMBER_OF_CARDS=5;
        String[] arrOfStr={};


        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("player:" + getNumJoueur());
            arrOfStr = (sc.nextLine()).split(" ", NUMBER_OF_CARDS);
            boolean pb=false;

            if(duplicates(arrOfStr)){
                System.out.println("You can't entry several times the same card");
                pb=true;
            }

            for (int i = 0; i < NUMBER_OF_CARDS; i++) {

                if ( !(hand[i].setValCarte(arrOfStr[i]))){
                    pb=true;
                    System.out.println("The card :" + arrOfStr[i]+" is not valid");
                    break;
                }
            }

            if (pb){
                System.out.print("Retry the entry \n");
                entry();
            }

            return arrOfStr;
        }
        catch(Exception e){
            System.out.println("Entry the right number of cards \n");

            entry();
        }
        return arrOfStr;
    }
}
