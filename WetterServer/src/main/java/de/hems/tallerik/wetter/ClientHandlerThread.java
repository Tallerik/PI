package de.hems.tallerik.wetter;

import de.hems.tallerik.wetter.lib.Socket;

import java.io.IOException;

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
        while (Main.running) {

            try {
                messageRecieved(s.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void messageRecieved(String text) throws IOException {
        String[] params = text.split(":");
        switch (params[0]) {
            case "add":
                db.add(text.replace("add:", ""));
            case "last":
                s.write(db.getLast().toEncodedString());
            case "all":
                for (DataSet d: db.getAll()) {
                    s.write(d.toEncodedString());
                }

        }
    }
}
