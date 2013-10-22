/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AppServer;

/**
 *
 * @author Fülöp Levente
 */
public class Player {
    
    private int id;
    private String name;

    public void setId(int id){
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public Boolean check_password(String password){
        return true;
    }
}
