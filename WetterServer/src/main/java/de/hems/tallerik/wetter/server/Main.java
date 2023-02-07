package de.hems.tallerik.wetter.server;

public class Main {

    public static boolean running = true;

    public static void main(String[] args) {
        Database db = new Database();
        Server s = new Server(1337, db);
    }
}