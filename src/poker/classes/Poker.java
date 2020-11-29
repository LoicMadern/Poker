package poker.classes;

class Poker {


    public static void main(String[] args) {
        while(true) {
            System.out.println("\nBeginning of the Poker game");
            Player player1 = new Player();
            Player player2 = new Player();

            CalculPoint pointPLayer1 = new CalculPoint();
            CalculPoint pointPlayer2 = new CalculPoint();


            player1.entry();
            player2.entry();

            /***************************************************************************************************************

             Example of entries:


             Player1:
             4C 10S 6S 6C 10H    //2 Pairs (6 & 10) Highest Card 4

             Player2:
             AD 6D 10C 6H 10D    //2 Pairs (6 & 10) Highest Card A


             The player 2 win with the highest card = A
             

             //value + color
             // String []specPossible = {"2", "3", "4", "5", "6", "7", "8","9", "10", "J", "Q", "K", "A"};
             // String [] possibleColor ={"C","H","S","D"};
             //do a space between each card
             //impossible to entry 2 or more times the same card

             ***************************************************************************************************************/

            while(Player.duplicatesallcard(player1.getHand(),player2.getHand())){
                System.out.println("You can't entry the same Card");
                player1.entry();
                player2.entry();
            }

            Compare joueur1Joueur2 = new Compare();

            pointPLayer1.setPoint(player1.getHand());
            pointPlayer2.setPoint(player2.getHand());
            joueur1Joueur2.findWinner(pointPLayer1.getPoint(), player1.getNumJoueur(),pointPlayer2.getPoint(), player2.getNumJoueur());
        }
    }

}
