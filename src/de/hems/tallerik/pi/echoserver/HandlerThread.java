package de.hems.tallerik.pi.echoserver;

import java.io.IOException;

public class HandlerThread extends Thread {

    private Socket socket;
    private EchoServer server;

    private String author = "";
    public HandlerThread(Socket s, EchoServer e) {
        socket = s;
        server = e;
    }

    @Override
    public void run() {
        boolean open = true;

        write("OK vom Echo-Server an Client.");

        try {
            String t = socket.readLine();
            if (t == null) {
                open = false;
            }
            author = t;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(open) {

            server.handleMessage("SERVER", author + " hat den Chat betreten");
        }

        while (open) {
            try {
                String data = socket.readLine();
                if(data == null) {
                    open = false;
                    break;
                }
                if(data.equals("ende")) {
                    write("Ende-OK vom Echo-Server und Tschuess");
                    server.handleMessage("SERVER", author + " hat den Chat verlassen");
                } else {
                    server.handleMessage(author, data);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        server.removeFromHandler(this);
    }

    public void write(String t) {
        try {
            socket.write(t + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
