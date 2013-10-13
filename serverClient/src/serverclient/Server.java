/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package serverclient;

import java.net.*;
import java.io.*;
import java.util.*;

public class Server extends Thread {

    /*Szerver port, kliensek listája <ArrayList>*/

    private final int port = 7777;

    private ServerSocket ss;

    private ArrayList<clientThread> clients = new ArrayList();

    public Server() {
        try {

            /* A szerver indítása a megadott porton. */

            System.out.println("\nA szerver indulásra kész a következő porton: " + port);
            ss = new ServerSocket(port);
            System.out.println("\nSzerver sikeresen elindúlt");
            String localhostname = java.net.InetAddress.getLocalHost().getHostName();
            System.out.println(localhostname);

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void run() {
        try {
            while (true) {
                /* Kliensekre várakozunk, melyek csatlakozni szeretnének */
                System.out.println("\nKliensre várakozunk...");
                addClient(); /* Elfogadjuk a csatlakozni próbálkozó klienseket. */
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public synchronized void handle(int ID, String input) throws IOException{

        /* A kliens üzeneteket olvassuk */

        System.out.println("\nÜzenet a klienstől: "+input);

        /* Ha a kliens üzenet a HELLO SERVER, akkor a szerver a kliensnek             HELLO CLIENT -el válaszol. */

        if(input.equals("HELLO SERVER"))
        {
            clients.get(findClient(ID)).send("HELLO CLIENT!");
        } else if(input.equals("HELLO SERVER2")){
            clients.get(findClient(ID)).send("HELLO CLIENT!2");
        } else if(input.equals("TESTQUERY")){
            Database db = new Database();
            clients.get(findClient(ID)).send(db.testQuery());
        }
        
        else {
            System.out.println("Answer to client" + ID);
            BufferedReader reader = new BufferedReader(new InputStreamReader (System.in));
            try{
                String s = reader.readLine();
                clients.get(findClient(ID)).send(s);
            }catch (IOException e){ };


        }
    }
    public void addClient() throws IOException {
        clientThread client = new clientThread(this, ss.accept());
        client.open(); /*Megnyítjuk a kliens IO stream-eket. */
        client.start();
        clients.add(client); /* Hozzáadjuk a klienset a kliens listához <ArrayList>*/
        System.out.println("\nKliens elfogadva.");
        client.send(">> ÜDV, KLIENS! ");

    }

    /* Removing the client */
    public void removeClient(int ID) {
        clients.remove(findClient(ID));
        System.out.println("\nA kliens eltávolítva.");
    }
/* Finding client position in the clients list*/
    public int findClient(int ID) {
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).ID == ID) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        new Server().start();
        //new Client("localhost", 7777).start();
    }

}
