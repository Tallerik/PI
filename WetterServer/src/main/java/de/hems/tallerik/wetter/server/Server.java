package de.hems.tallerik.wetter.server;

import de.hems.tallerik.wetter.server.lib.ServerSocket;
import de.hems.tallerik.wetter.server.lib.Socket;

import java.io.IOException;

public class Server {

    private ServerSocket ss;
    private Database db;
    private int port;

    public Server(int port, Database db) {
        try {
            this.db = db;
            this.port = port;
            ss = new ServerSocket(port);
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {

        try {
            while (Main.running) {

                Socket s = ss.accept();
                ClientHandlerThread ch = new ClientHandlerThread(s, db);

            }
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
