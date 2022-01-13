package ca.sfu.cmpt213.a2.model;

import java.util.Random;
import java.util.Scanner;

/**
 *The Maze class is the core class of the game,
 * which is a modeling of the maze game. The data includes
 * maps, hunter, guardian and relics.
 */
public class Maze {
    public Map map;
    Hunter hunter;
    Guardian g1;
    Guardian g2;
    Guardian g3;
    int relics;
    int totalRelics;
    boolean[][] visibleSet;
    public Maze(){
        relics=0;
        totalRelics=3;
        map=new Map();
        hunter=new Hunter();
        g1=new Guardian(1,18);
        g2=new Guardian(14,1);
        g3=new Guardian(14,18);

        visibleSet=new boolean[15][19];
    }
    public int getInput(){
        boolean getIn=false;
        while(!getIn) {
            System.out.print("Enter your move [WASD?]: ");
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextLine()) {
                String input = scanner.next();
                if (input.equals("W")||input.equals("w")) {
                    getIn=true;
                    return 0;
                }
                if (input.equals("S")||input.equals("s")) {
                    getIn=true;
                    return 1;
                }
                if (input.equals("A")||input.equals("a")) {
                    getIn=true;
                    return 2;
                }
                if (input.equals("D")||input.equals("d")) {
                    getIn=true;
                    return 3;
                }
                if(input.equals("?")){
                    getIn=true;
                    return 4;
                }
                if(input.equals("C")||input.equals("c")){
                    cheatCode();
                    continue;
                }
                if(input.equals("M")||input.equals("m")){
                    //showEntireMap();
                    getIn=true;
                    return 5;
                }

            }

            System.out.println("Invalid input, please enter [W,S,A,D].\n");

        }
        return -1;
    }
    public int checkNextStep(int n){
        //input: 0:up 1:down 2:lift 3:right
        //return 0:false 1:true 2:game over 3:get relics
        int x=hunter.getX();
        int y= hunter.getY();
        if(n==0){
            x=x-1;
        }
        if(n==1){
            x=x+1;
        }
        if(n==2){
            y=y-1;
        }
        if(n==3){
            y=y+1;
        }
        if(map.getMap()[x][y].getSymbol()==0){
            System.out.println("Invalid move: you cannot move through walls!\n");
            return 0;
        }
        if(map.getMap()[x][y].getSymbol()==2){
            System.out.println("Oh no! The hunter has been killed!");
            return 2;
        }
        if(map.getMap()[x][y].getSymbol()==3){
            System.out.println("Congratulation,you get one relics.");

            return 3;
        }if(map.getMap()[x][y].getSymbol()==4){

            return 1;
        }
        return 0;
    }
    public void hunterExchange(int m){
        //input: 0:up 1:down 2:lift 3:right
        map.getMap()[hunter.getX()][hunter.getY()].setSymbol(4);
        if(m==0){
            hunter.setX(hunter.getX()-1);
            hunter.setY(hunter.getY());
        }
        if(m==1){
            hunter.setX(hunter.getX()+1);
            hunter.setY(hunter.getY());
        }
        if(m==2){
            hunter.setX(hunter.getX());
            hunter.setY(hunter.getY()-1);
        }
        if(m==3){
            hunter.setX(hunter.getX());
            hunter.setY(hunter.getY()+1);
        }
        map.getMap()[hunter.getX()][hunter.getY()].setSymbol(1);
        map.getMap()[hunter.getX()][hunter.getY()].setVisible(true);
    }

    public void setVisible(){
        int x=hunter.getX();
        int y= hunter.getY();
        map.getMap()[x-1][y-1].setVisible(true);
        map.getMap()[x-1][y].setVisible(true);
        map.getMap()[x-1][y+1].setVisible(true);
        map.getMap()[x][y-1].setVisible(true);
        map.getMap()[x][y+1].setVisible(true);
        map.getMap()[x+1][y-1].setVisible(true);
        map.getMap()[x+1][y].setVisible(true);
        map.getMap()[x+1][y+1].setVisible(true);
    }
    public void showRule(){
        System.out.println();
        System.out.println("DIRECTIONS:");
        System.out.println("      Collect 3 relics!");
        System.out.println("LEGEND:");
        System.out.println("#: Wall\n" +
                "\t@: You (the treasure hunter)\n" +
                "\t!: Guardian\n" +
                "\t^: Relic\n" +
                "\t.: Unexplored space");
        System.out.println("MOVES:\n" +
                "\tUse W (up), A (left), S (down) and D (right) to move.\n" +
                "\t(You must press enter after each move).\n");
    }
    public void cheatCode(){
        if(relics<1){
            totalRelics=1;
        }
        else{
            System.out.println("This cheat code will only be used before the first relic is collected.");
        }
    }
    public void showEntireMap(){

        for(int i=0;i<15;i++){
            for(int j=0;j<19;j++){
                visibleSet[i][j]=map.getMap()[i][j].getVisible();
                map.getMap()[i][j].setVisible(true);
            }
        }
    }
    public void restoreMapVisible(){
        for(int i=0;i<15;i++){
            for(int j=0;j<19;j++){
                map.getMap()[i][j].setVisible(visibleSet[i][j]);
            }
        }
    }
    public void start(){
        int step=0;
        String title=") Start";
        int move=-1;
        int check=-1;
        while(true){

            System.out.println("----------");
            System.out.println((step+1) +title);
            System.out.println("----------");
            if(step==0){
                showRule();
            }
            System.out.println("Maze:");
            setVisible();
            map.showMap();
            if(move==5){
                restoreMapVisible();
            }
            System.out.println("Total number of relics to be collected: "+totalRelics);
            System.out.println("Number of relics currently in possession: "+relics);

            move=getInput();
            if(move==4){
                showRule();
                continue;
            }
            if(move==5){
                title=") Map Reveal";
                showEntireMap();
            }
            //check 0:false 1:true 2:game over 3:get relics
            check=checkNextStep(move);

            if(check==1){
                title=") Next Step";

                hunterExchange(move);
               // map.showMap();
            }
            if(check==2){
                //over
                title=")  Getting killed by a guardian";
                hunterExchange(move);
                //map.showMap();
                map.getMap()[hunter.getX()][hunter.getY()].setSymbol(5);
                System.out.println("GAME OVER... please try again.");
                break;
            }
            if(check==0){
                title=")  Illegal Move";

                continue;
            }
            if(check==3){
                hunterExchange(move);
                //map.showMap();
                relics=relics+1;
                title=") Collecting a relic";
                if(relics==3){
                    title=") Winning the game";

                    System.out.println("Congratulations! You won!\n");
                    break;
                }
            }


            step++;
            GuardianMove(g1);
            GuardianMove(g2);
            GuardianMove(g3);


        }
        for(int i=0;i<15;i++){
            for(int j=0;j<19;j++){
                map.getMap()[i][j].setVisible(true);
            }
        }
        map.showMap();

    }
    public void GuardianMove(Guardian g){
        //randomWay 0:up 1:down 2:lift 3:right
        Random random=new Random();
        for(int i=0;i<10;i++){
            int moveWay=random.nextInt(4);
            if(guardianCheckNextStep(g,moveWay)==1){
                guardianExchange(g,moveWay);

                break;
            }
        }

    }
    public int guardianCheckNextStep(Guardian guardian,int n){
        //input: 0:up 1:down 2:lift 3:right
        //return 0:false 1:true 2:is relic
        int x=guardian.getX();
        int y= guardian.getY();
        if(n==0){
            x=x-1;
        }
        if(n==1){
            x=x+1;
        }
        if(n==2){
            y=y-1;
        }
        if(n==3){
            y=y+1;
        }
        if(map.getMap()[x][y].getSymbol()==0){
            return 0;
        }
        if(map.getMap()[x][y].getSymbol()==1){
            return 0;
        }
        if(map.getMap()[x][y].getSymbol()==3){
            return 0;
        }

        if(map.getMap()[x][y].getSymbol()==4){
            return 1;
        }
        return 0;
    }
    public void guardianExchange(Guardian g,int m){
        //input: 0:up 1:down 2:lift 3:right
        map.getMap()[g.getX()][g.getY()].setSymbol(4);
        map.getMap()[g.getX()][g.getY()].setVisible(false);
        if(m==0){
            g.setX(g.getX()-1);
            g.setY(g.getY());
        }
        if(m==1){

            g.setX(g.getX()+1);
            g.setY(g.getY());
        }
        if(m==2){
            g.setX(g.getX());
            g.setY(g.getY()-1);
        }
        if(m==3){
            g.setX(g.getX());
            g.setY(g.getY()+1);
        }
        map.getMap()[g.getX()][g.getY()].setSymbol(2);
        map.getMap()[g.getX()][g.getY()].setVisible(true);
    }
}
