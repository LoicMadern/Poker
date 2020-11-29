package poker.classes;

import java.util.Arrays;

class CalculPoint {



    private int decalageCombine = 7 * 4;
    private int decalageValCombine = 6 * 4;
    private int decalageValCombine2 = 5 * 4;
    private int[] decalageCard = {0, 4, 2 * 4, 3 * 4, 4 * 4};
    private int[] playerHand = {0, 0, 0, 0, 0};
    private String[] playerHandColor = {"Club","Diamond","Spide","Heart"};
    private int[] indexColor = {0, 0, 0, 0, 0};
    private Points playerpoints = new Points();
    private static final int NUMBER_OF_CARDS = 5;

    //playerpoints is an 0x on 32 bits equivalent as 8 numbers in 0x => (0x12345678)
    //The numbers in the interval [0 - 4] weak weight represent the value of the hand each number by card (5)
    //The 5rd number is for the  Pair, Three of a kind, Four a kind, the 3 numbers of the Full House and the head of the Straight
    //The 6rd number if for the second the value for The Two Pair
    //The 7rd number is for the value de la combination. 0 if nothing, 1 if ther's a Pair



    void setPoint(Card[] main) {
        for (int i = 0; i < NUMBER_OF_CARDS; i++) {
            playerHand[i] = main[i].getValCarte();
            indexColor[i] = main[i].getIndexCouleur();
        }
        Arrays.sort(playerHand);

        setPointsQuinteFlush();
        setPointsCard();
    }



    void setPointsQuinteFlush() {

        if(setPointsFlash() && setPointsStraight()) {
            playerpoints.reset();
            if(playerHand[4] == 13 && playerHand[3] == 3) {
                playerpoints.setPointsPlayer((playerHand[3] << decalageValCombine) + (0x7 << decalageCombine));
            }
            else {
                playerpoints.setPointsPlayer((playerHand[4] << decalageValCombine) + (0x7 << decalageCombine));
            }
        }else {
            playerpoints.reset();
            setPointsFourOfKind();
        }
    }
    void setPointsFourOfKind() {

        for (int i = 0; i < NUMBER_OF_CARDS - 3; i++) {
            if ((playerHand[i] == playerHand[i + 3])) {
                playerpoints.setPointsPlayer((playerHand[i] << decalageValCombine) + (0x6 << decalageCombine));
            }
        }
        if (playerpoints.getPlayerPoints() == 0)
            setPointsFull();
    }
    void setPointsFull() {
        int full = -1;
        int cpt = 3;

        for (int i = 0; i < NUMBER_OF_CARDS - 2; i++) {
            if ((playerHand[i] == playerHand[i + 2])) {
                full = playerHand[i];
                if(i == 2)
                    cpt = 0;
            }
        }
        if(playerHand[cpt] == playerHand[cpt+1] && full != -1) {
            playerpoints.setPointsPlayer((full << decalageValCombine) + (playerHand[cpt] << decalageValCombine2) + (0x5 << decalageCombine));
        }
        if (playerpoints.getPlayerPoints() == 0)
            setPointsFlash();
    }
    boolean setPointsFlash(){
        int cpt = 0;
        while(cpt< NUMBER_OF_CARDS -1){
            if(indexColor[cpt] == indexColor[cpt+1]) {
                cpt++;
            }
            else
                break;
        }
        if(cpt >= 4) {
            playerpoints.setPointsPlayer(0x4 << decalageCombine);
            playerpoints.setColorPlayer(playerHandColor[indexColor[0]]);
            return true;
        }
        else
            setPointsStraight();
        return false;
    }
    boolean setPointsStraight() {
        int cpt = 0;
        for (int i = 0; i < NUMBER_OF_CARDS - 1; i++) {
            if((playerHand[i] + 1 == playerHand[i + 1])) {
                cpt++;
            }
            else
                break;
        }
        if(cpt == 4) {
            playerpoints.setPointsPlayer((playerHand[4] << decalageValCombine) + (0x3 << decalageCombine));
            return true;
        }
        else if(cpt == 3 && playerHand[0] == playerHand[4]-13) {
            playerpoints.setPointsPlayer((playerHand[3] << decalageValCombine) + (0x3 << decalageCombine));
            return true;
        }
        if (playerpoints.getPlayerPoints() == 0)
            setPointsThreeOfKind();
        return false;
    }
    void setPointsThreeOfKind() {
        for (int i = 0; i < NUMBER_OF_CARDS - 2; i++) {
            if ((playerHand[i] == playerHand[i + 2])) {
                playerpoints.setPointsPlayer((playerHand[i] << decalageValCombine) + (0x2 << decalageCombine));
            }
        }

        if (playerpoints.getPlayerPoints() == 0)
            setPointsPair();

    }
    void setPointsPair() {
        int[] deuxPaire1 = {0, 0};
        int k1 = 0;

        for (int i = 0; i < NUMBER_OF_CARDS - 1; i++) {
            if (playerHand[i] == playerHand[i + 1]) {
                deuxPaire1[k1] = playerHand[i];
                k1++;
            }
        }
        if (k1 == 2) {
            Arrays.sort(deuxPaire1);
            playerpoints.setPointsPlayer(0x1 << decalageCombine);
            playerpoints.setPointsPlayer(deuxPaire1[0] << decalageValCombine2);
            playerpoints.setPointsPlayer(deuxPaire1[1] << decalageValCombine);
        }
        else
            playerpoints.setPointsPlayer(deuxPaire1[0] << decalageValCombine);
    }
    void setPointsCard() {
        for (int num_card = NUMBER_OF_CARDS - 1; num_card >= 0; num_card--) {
            playerpoints.setPointsPlayer(playerHand[num_card] << decalageCard[num_card]);
        }

    }
    Points getPoint() {
        return playerpoints;
    }



}
