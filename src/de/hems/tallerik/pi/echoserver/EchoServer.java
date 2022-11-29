package de.hems.tallerik.pi.echoserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EchoServer {
    private ServerSocket socket;
    private List<HandlerThread> handlerList;

    public EchoServer(int port) {
        handlerList = new ArrayList<>();
        try {
            socket = new ServerSocket(port);
            while (true) {
                Socket s = socket.accept();
                HandlerThread handler = new HandlerThread(s, this);
                handlerList.add(handler);
                handler.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void removeFromHandler(HandlerThread h) {
        handlerList.remove(h);
    }

    public void handleMessage(String author, String msg) {
        String message = "Nachricht von " + author+ ": "+msg;
        System.out.println(message);
        for (HandlerThread h: handlerList) {
            h.write(message);
        }
    }

    public static void main(String[] args) {
        new EchoServer(2000);
    }
}
