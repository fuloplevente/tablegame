/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AppServer;

/**
 *
 * @author Fülöp Levente
 */
public abstract class Game {
    
    public Player[] players;
    private int id;
    protected Object state;

    public Game() {
        players = new Player[2];
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public abstract void startGame(Player[] players);
    public abstract void closeGame();
    public abstract void step(Object step);
    
}
