package de.hems.tallerik.wetter.server;

import de.hems.tallerik.wetter.server.lib.Socket;

import java.io.IOException;
import java.net.SocketException;

public class ClientHandlerThread extends Thread {

    private Socket s;
    private Database db;

    public ClientHandlerThread(Socket s, Database db) {
        this.db = db;
        this.s = s;
        this.start();
    }

    @Override
    public void run() {
        boolean run = true;
        while (Main.running && run) {

            try {
                messageRecieved(s.readLine());
            } catch (SocketException e) {
                run = false;

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void messageRecieved(String text) throws IOException {
        String[] params = text.split(":");
        switch (params[0]) {
            case "add":
                db.add(text.replace("add:", ""));
            case "last":
                s.write(db.getLast().toEncodedString() + "\n");
            case "all":
                for (DataSet d: db.getAll()) {
                    s.write(d.toEncodedString() + "\n");
                }

        }
    }
}
