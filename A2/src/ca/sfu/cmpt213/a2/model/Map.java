package ca.sfu.cmpt213.a2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Map class is used to model a random map
 * with a size of 20 in length and 16 in width.
 * The data includes a 16*20 two-dimensional array
 * of Cell type to store map information, and a
 * Cell array of size 3 to store Relics information.
 */
public class Map {
    public final Cell[][] map; //00:hunter  (0,13)(17,0)(17,13):guardian
    public final Cell [] Relics; //size:3 Random



    public Map(){
        map=new Cell[16][20];
        Relics=new Cell[3];
        for(int i=0;i<3;i++){
            Relics[i]=new Cell();
            Relics[i].setSymbol(3);
        }
        for(int i=0;i<16;i++) {
            for (int j = 0; j < 20; j++) {
                map[i][j]=new Cell();
                map[i][j].setIndexRow(i);
                map[i][j].setIndexCol(j);
                map[i][j].setSymbol(0);
                map[i][j].setIndexRow(i);
                map[i][j].setIndexCol(j);
                //System.out.println("test"+i+"+"+j+"ads"+map[i][j].getSymbol());
            }
        }
        //map[0][0]=new Cell();
        map[1][1].setSymbol(1);
        //map[0][13]=new Cell();
        map[1][18].setSymbol(2);
        //map[17][0]=new Cell();
        map[14][1].setSymbol(2);
        //map[17][13]=new Cell();
        map[14][18].setSymbol(2);

        for(int i=0;i<19;i++){
            map[0][i].setVisible(true);
            map[15][i].setVisible(true);
        }
        for(int i=0;i<16;i++){
            map[i][0].setVisible(true);
            map[i][19].setVisible(true);
        }
        map[1][1].setVisible(true);
        map[1][18].setVisible(true);
        map[14][1].setVisible(true);
        map[14][18].setVisible(true);
        createRandomMap();
    }
    public void setRandomRelics(){
        int count=0;
        while(count<3){
            Cell temp=new Cell();
            Random randomRow = new Random();
            temp.setIndexRow( randomRow.nextInt(16));
            Random randomCol = new Random();
            temp.setIndexCol(randomCol.nextInt(20));
            if(map[temp.getIndexRow()][temp.getIndexCol()].getSymbol()==0&&temp.getIndexRow()>1
                    &&temp.getIndexRow()<14&&temp.getIndexCol()>1&&temp.getIndexCol()<18){

                map[temp.getIndexRow()][temp.getIndexCol()].setSymbol(3);
                map[temp.getIndexRow()][temp.getIndexCol()].setIndexRow(temp.getIndexRow());
                map[temp.getIndexRow()][temp.getIndexCol()].setIndexCol(temp.getIndexCol());
                Relics[count].setIndexRow(temp.getIndexRow());
                Relics[count].setIndexCol(temp.getIndexCol());

                count++;
            }

        }
    }
    public void createRandomMap(){
        setRandomRelics();
        int[]aroundX={-1,1,0,0};//up down left right
        int[] aroundY={0,0,-1,1};//up down left right
        ArrayList<Integer> X=new ArrayList<Integer>();
        ArrayList<Integer> Y=new ArrayList<Integer>();

        X.add(1);Y.add(1);
        X.add(1); Y.add(18);
        X.add(14);Y.add(1);
        X.add(14);Y.add(18);
        //X.add(Relics[0].getIndexRow());Y.add(Relics[0].getIndexCol());
//        X.add(Relics[1].getIndexRow());Y.add(Relics[1].getIndexCol());
//        X.add(Relics[2].getIndexRow());Y.add(Relics[2].getIndexCol());
        while(X.size()!=0){
            Random random_wall=new Random();
            int index=random_wall.nextInt(X.size());
            int x=X.get(index);
            int y =Y.get(index);

            //get way from up down left right
            int countWay=0;
            int r=0;
            int c=0;
            for(int i=0;i<4;i++){
                r=x+aroundX[i];
                c=y+aroundY[i];
                if(map[r][c].getSymbol()==4){
                    countWay++;
                }
            }
            if(countWay<=1){
                map[x][y].setSymbol(4);
                for(int i=0;i<4;i++){
                    r=x+aroundX[i];
                    c=y+aroundY[i];
                    if(map[r][c].getSymbol()==0&&map[r][c].getIndexRow()!=0
                            &&map[r][c].getIndexRow()!=15&&map[r][c].getIndexCol()!=0&&map[r][c].getIndexCol()!=19){
                        X.add(r);
                        Y.add(c);
                    }
                }
            }
            X.remove(index);
            Y.remove(index);
        }

        for(int i=2;i<18;i++){
            if(map[1][i].getSymbol()==0&&map[1][i+1].getSymbol()==0){
                map[1][i+1].setSymbol(4);
            }
            if(map[14][i].getSymbol()==0&&map[14][i+1].getSymbol()==0){
                map[14][i+1].setSymbol(4);
            }
        }
        for(int i=2;i<14;i++){
            if(map[i][1].getSymbol()==0&&map[i+1][1].getSymbol()==0){
                map[i+1][1].setSymbol(4);
            }
            if(map[i][18].getSymbol()==0&&map[i+1][18].getSymbol()==0){
                map[i+1][18].setSymbol(4);
            }

        }
        removeInnerWall();

        map[1][1].setSymbol(1);
        map[1][18].setSymbol(2);
        map[14][1].setSymbol(2);
        map[14][18].setSymbol(2);
        map[Relics[0].getIndexRow()][Relics[0].getIndexCol()].setSymbol(3);
        map[Relics[1].getIndexRow()][Relics[1].getIndexCol()].setSymbol(3);
        map[Relics[2].getIndexRow()][Relics[2].getIndexCol()].setSymbol(3);
        if(map[1][2].getSymbol()==0&&map[2][1].getSymbol()==0){
            map[1][2].setSymbol(4);
        }
        if(map[1][13].getSymbol()==0&&map[2][14].getSymbol()==0){
            map[1][13].setSymbol(4);
        }
        if(map[13][1].getSymbol()==0&&map[14][2].getSymbol()==0){
            map[17][1].setSymbol(4);
        }
        if(map[14][17].getSymbol()==0&&map[13][18].getSymbol()==0){
            map[18][13].setSymbol(4);
        }
        chekSquareConstraint();

    }
    public void removeInnerWall(){
        for(int i=1;i<14;i++){
            for(int j=1;j<18;j++){
                if(map[i][j].getSymbol()==0){
                    if(checkInnerWall(i,j)==1){
                        map[i][j].setSymbol(4);
                    }
                }
            }
        }


    }
    public int checkInnerWall(int i,int j){
        //return 0: false
        if(map[i-1][j].getSymbol()==4&&map[i][j-1].getSymbol()==4&&map[i-1][j-1].getSymbol()==4){
            return 0;
        }
        if(map[i-1][j].getSymbol()==4&&map[i][j+1].getSymbol()==4&&map[i-1][j+1].getSymbol()==4){
            return 0;
        }
        if(map[i+1][j].getSymbol()==4&&map[i][j-1].getSymbol()==4&&map[i+1][j-1].getSymbol()==4){
            return 0;
        }
        if(map[i+1][j].getSymbol()==4&&map[i][j+1].getSymbol()==4&&map[i+1][j+1].getSymbol()==4){
            return 0;
        }
        return 1;
    }
    public void chekSquareConstraint(){
        boolean satisfy1=false;
        boolean satisfy2=false;
        while(!satisfy1 || !satisfy2) {
            for (int i = 1; i < 14; i++) {
                for (int j = 1; j < 18; j++) {
                    if (map[i][j].getSymbol() == 0 && map[i][j + 1].getSymbol() == 0
                            && map[i + 1][j].getSymbol() == 0 && map[i + 1][j + 1].getSymbol() == 0) {
                        Random deleteWall = new Random();
                        if (deleteWall.nextInt(4) == 1) {
                            map[i][j].setSymbol(4);
                        }
                        if (deleteWall.nextInt(4) == 2) {
                            map[i + 1][j + 1].setSymbol(4);
                        } else {
                            map[i][j + 1].setSymbol(4);
                            map[i + 1][j].setSymbol(4);
                        }
                    }else{
                        satisfy1=true;
                    }
                }
            }
            for (int i = 1; i < 14; i++) {
                for (int j = 1; j < 18; j++) {
                    if (map[i][j].getSymbol() == 4 && map[i][j + 1].getSymbol() == 4
                            && map[i + 1][j].getSymbol() == 4 && map[i + 1][j + 1].getSymbol() == 4) {
                        Random deleteBlank = new Random();
                        if (deleteBlank.nextInt(4) == 1) {
                            map[i][j].setSymbol(0);
                        }
                        if (deleteBlank.nextInt(4) == 2) {
                            map[i + 1][j + 1].setSymbol(0);
                        } else {
                            map[i + 1][j].setSymbol(4);
                        }
                    }
                    else{
                        satisfy2=true;
                    }
                }
            }
        }

    }
    public void showMap(){
        //-1:initialization    0:Wall      1:hunter
        // 2:guardians    3:relics   4:blank
        for(int i=0;i<16;i++){
            for(int j=0;j<20;j++){
                if(map[i][j].getVisible()==true) {
                    if (map[i][j].getVisible()) {
                        if (map[i][j].getSymbol() == 1) {
                            System.out.print('@');

                        }
                        if (map[i][j].getSymbol() == 0) {
                            System.out.print('#');
                        }
                        if (map[i][j].getSymbol() == 2) {
                            System.out.print('!');
                        }
                        if (map[i][j].getSymbol() == 3) {
                            System.out.print('^');
                        }
                        if (map[i][j].getSymbol() == 4) {
                            System.out.print(' ');
                        }
                        if (map[i][j].getSymbol() == 5) {
                            System.out.print('X');
                        }
                    }
                } else{
                    System.out.print('.');
                }
                //System.out.print(map[i][j].getIndexRow()+","+map[i][j].getIndexCol()+"  ");
            }
            System.out.println();
        }

    }
    public Cell[] getRelics() {
        return Relics;
    }

    public Cell[][] getMap() {
        return map;
    }
}
