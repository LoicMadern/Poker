package poker.classes;

class Points {



    String getPlayerColor() {
        return color;
    }

    private long points = 0x0;

    private String color;

    void setPointsPlayer(long points) {
        this.points += points;
    }

    void reset() {
        this.points = 0x0;
    }

    void setColorPlayer(String color) {
        this.color = color;
    }

    long getPlayerPoints() {
        return points;
    }

}
