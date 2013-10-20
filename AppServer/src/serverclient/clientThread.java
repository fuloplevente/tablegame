/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package serverclient;

/**
 *
 * @author TANGOSTAR
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class clientThread extends Thread {

private Socket s;
private DataOutputStream out;
private DataInputStream in;
public int ID = -1;
private Server srv;

public clientThread(Server srv, Socket s) {
    this.srv = srv;
    this.s = s;
    /* Beállítjuk a kliens ID -t*, a portbol kapjuk. */
    ID = s.getPort();
}
public void run() {
    try {
        while (true) {
            /* A szerver oldali üzenetek olvasása. */
            srv.handle(ID, (String) in.readUTF());
        }
    } catch (IOException e) {
        /* Ha valami olvasási probléma van, eltávolítjuk a klienset. */
        srv.removeClient(ID);
    }
}


public void send(String msg) throws IOException {
    /* Üzenet küldése a szervernek */
    out.writeUTF(msg);
}
public void open() throws IOException {
    /* IO stream-ek megnyítása, miután a socket kapcsolódva van. */
    out = new DataOutputStream(s.getOutputStream());
    in = new DataInputStream(s.getInputStream());
}
public void close() throws IOException {
    /* Socket bezárása (kapcsolat) és stream-ek bezárása. */
    in.close();
    out.close();
    s.close();
}
}
