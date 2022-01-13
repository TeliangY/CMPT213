package ca.sfu.cmpt213.a2.model;

/**
 * The Hunter class is used to model the hunter
 * in the game. The data includes the coordinates
 * of the hunter in the map and the distance the
 * hunter can move each time it moves.
 */
public class Hunter {
    private int x;
    private int y;
    private final int[][] moveArr;
   // private int moveTo;//0: up  1:down  2:lift  3:right
    public Hunter() {
        x=1;
        y=1;

        moveArr=new int[4][2];
        //current position plus each row(x,y),then get new position
        moveArr[0][0]=-1;//move up
        moveArr[0][1]=0;
        moveArr[1][0]=1;//move down
        moveArr[1][1]=0;
        moveArr[2][0]=0;//move lift
        moveArr[2][1]=-1;
        moveArr[3][0]=0;
        moveArr[3][1]=1;//move right

        //moveTo=-1;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int[][] getMoveArr() {
        return moveArr;
    }
}
