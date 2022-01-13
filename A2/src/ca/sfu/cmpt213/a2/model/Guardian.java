package ca.sfu.cmpt213.a2.model;

/**
 * The Guardian class is used to model the guardian.
 * The data includes guardian coordinates in the map.
 */
public class Guardian {
    private int x;
    private int y;
    public Guardian(int x, int y){
        this.x=x;
        this.y=y;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
