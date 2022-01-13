package ca.sfu.cmpt213.a2.textui;

import ca.sfu.cmpt213.a2.model.Game;
import ca.sfu.cmpt213.a2.model.Maze;

/**
 * The UI class is used to encapsulate the entire game project.
 */
public class UI {
    private Game game;
    public UI(){
        game=new Game();
    }
    public void play(){
        game.loadGame();
    }
}
