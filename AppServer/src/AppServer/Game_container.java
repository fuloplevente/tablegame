/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AppServer;
import java.util.ArrayList; 

/**
 *
 * @author Fülöp Levente
 */
public class Game_container {
    
    public ArrayList<Game> games;

    public Game_container() {
        games = new ArrayList<Game>();
    }
    
    public void addGame(Game game){
        games.add(game);
    }
    
    public void deleteGame(Game game){
        games.remove(game);
    }
    
}
