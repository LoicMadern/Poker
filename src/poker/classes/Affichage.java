package poker.classes;

class Affichage {



    void winner(Points winnerPoints, Points looserPoints, int looser){

        final String []combinaisons = {
                " Pair of ",
                " Pair of ",
                " Three of a kind ",
                " Straight with the highest card ",
                " Flush ",
                " a Full house with ",
                " Four a kind ",
                " Straight Flush "};

        final String []specPossible = { "2", "3", "4", "5", "6", "7", "8",
                "9", "10", "J", "Q", "K", "A"};

        long pointsPlayerWinner = winnerPoints.getPlayerPoints();
        long pointsPlayer2 = looserPoints.getPlayerPoints();
        String CouleurPlayerWinner = winnerPoints.getPlayerColor();

        final int decalageCombine = 7*4;
        final int decalageValCombine = 6*4;
        final int decalageValCombine2 = 5*4;

        int cpt = decalageCombine;
        int numCombineWinner = (int) (pointsPlayerWinner>>cpt)&0xf;
        int valCombineWinner = (int) (pointsPlayerWinner>>decalageValCombine)&0xf;
        int valCombineWinner2 = (int) (pointsPlayerWinner>>(decalageValCombine2))&0xf;
        int valCarteWinner;

        long combinePlayer2 = (pointsPlayer2>>decalageCombine)&0xf;
        long combineWinner = (pointsPlayerWinner>>decalageCombine)&0xf;


        while(combineWinner == combinePlayer2){
            cpt -= 4;
            combineWinner = (pointsPlayerWinner>>cpt) & 0xf;
            combinePlayer2 = (pointsPlayer2>>cpt) & 0xf;
        }

        System.out.print("The player " + looser + " win ");
        //display of all the combinations
        if(cpt >= decalageValCombine2){
            System.out.print("with" + combinaisons[numCombineWinner]);

            //Straight Flush and Flush
            if(numCombineWinner == 0x4 || numCombineWinner == 0x7)
                System.out.print(CouleurPlayerWinner);
            else
                System.out.print(specPossible[valCombineWinner]);

            if(numCombineWinner == 0x7){
                System.out.print("with the highest card = " + specPossible[valCombineWinner]);
            }

            //Pair and Full House
            if(numCombineWinner == 0x1 || numCombineWinner == 0x5)
                System.out.print("and a Pair of " + specPossible[valCombineWinner2]);

            System.out.println();
        }
        //Highest Card
        else{
            valCarteWinner = (int) (pointsPlayerWinner>>cpt)&0xf;
            System.out.println("with the highest card = " + specPossible[valCarteWinner]);
        }
    }

    void equality(){
        System.out.println("egality");
    }
}
