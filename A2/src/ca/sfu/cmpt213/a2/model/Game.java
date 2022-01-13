package ca.sfu.cmpt213.a2.model;

import java.util.Scanner;

/**
 * The Game class is used to finalize and encapsulate
 * the game. Contains only one maze type data. The user
 * can repeat the game until he wants to launch the game,
 * and after each repetition, a new map is displayed.
 */
public class Game {
    private Maze maze;
    public Game(){
        maze=new Maze();
    }
    public void loadGame(){
        maze.start();
        while(true) {
            System.out.println("Do you want to try again?\n" +
                    "Enter Y to continue, enter other characters to end the game. ");
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextLine()) {
                String y = scanner.next();
                if (y.equals("y") || y.equals("Y")) {
                    maze=new Maze();
                    maze.start();
                } else {
                    System.out.println("Thank you for your support,\n" +
                            " look forward to your next play.");
                    break;
                }
            }
        }
    }
}
