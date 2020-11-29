package poker.classes;

class Card {

    private int valCarte;
    private int colorCard;

    String []specPossible = {"2", "3", "4", "5", "6", "7", "8",
            "9", "10", "J", "Q", "K", "A"};
    String [] possibleColor ={"C","H","S","D"};

    Card(){
        valCarte = 0;
        colorCard = 0;
    }


    public String toString(){
        String specCarte;
        specCarte = specPossible[valCarte]+ possibleColor[colorCard]+" ";
        return specCarte;
    }

    boolean setValCarte(String specCarte){
        for(int i = 0; i < 13; i++){
            for(int j=0;j<4;j++) {

                if (specCarte.equals(specPossible[i] + possibleColor[j])) {

                    valCarte = i;
                    colorCard = j;


                    return true;

                }
            }
        }
        return false;
    }

    int getValCarte(){
        return valCarte;
    }

    int getIndexCouleur(){
        return colorCard;
    }
}
