package poker.classes;

class Compare {


    Affichage display = new Affichage();

    void findWinner(Points pointsPlayer1, int numPlayer1, Points pointsPlayer2, int numPlayer2){

        if(pointsPlayer1.getPlayerPoints() > pointsPlayer2.getPlayerPoints()) {
            display.winner(pointsPlayer1,pointsPlayer2,numPlayer1);
        }else if(pointsPlayer1.getPlayerPoints() == pointsPlayer2.getPlayerPoints()){
            display.equality();
        }else {
            display.winner(pointsPlayer2,pointsPlayer1,numPlayer2);
        }
        System.out.println();
    }
}
