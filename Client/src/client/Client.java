/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

/**
 *
 * @author TANGOSTAR
 */
import java.io.*;
import java.net.*;

public class Client extends Thread {

    private Socket s;
    private DataInputStream in;
    private DataOutputStream out;

    public Client(final String host, final int port) {

        /* Probálkozunk kapcsolatot létesíteni a szerverrel */

        try {
            System.out.println("Kapcsolódás a szerverhez: " + host + " és port: " + port);
            s = new Socket(host, port);
            if(s.isConnected())
            {
                /* Ha sikerült kapcsolódni,                 akkor megnyítjuk a stream-eket és egy üdvözlő üzenetet                 küldünk a szervernek. */

                open();
                System.out.println("Kapcsolódva a szerverhez: " + host + " és port: " + port);
                send("HELLO SERVER");
                String message = in.readUTF();
                System.out.println("Üzenet a szervertől: "+message);
            }

        } catch (IOException e) {
            /* Sikertelen kapcsolódás esetén hiabüzenet.. */
            System.out.println("Nem sikerült csatlakozni a szerverhez. " + e.getMessage());
        }
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader (System.in));
            while (true) {
                /* A szervertől kapott üzenetek olvasása. */
                String message = in.readUTF();
                System.out.println("Üzenet a szervertől: "+message);

                System.out.print(">> ");
                send(reader.readLine());  
            }
        } catch (IOException e) {
            /* Olvasási problémák, kapcsolat megszakítása. */
            close();
            System.out.println("Kapcsolat megszakítva. " + e.getMessage());
        }
    }

    public void open() throws IOException {

        /* IO stream-ek megnyítása, miután a kapcsolat létrejött.*/

        in = new DataInputStream(s.getInputStream());
        out = new DataOutputStream(s.getOutputStream());
    }

    public void close() {
        /* Lezárjuk a kliens stream-eket. */
        try {
            out.close();
            in.close();
            s.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /* Ünzenet küldése a szervernek. */
    public void send(String msg) throws IOException {
        out.writeUTF(msg);
    }

    public static void main(String[] args) {
        String host = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader (System.in));
        System.out.print("Host: ");
        try{
            host = reader.readLine();
        }catch (IOException e){ };
        new Client(host, 7777).start();
    }
}

