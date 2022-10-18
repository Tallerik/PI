package de.hems.tallerik.pi.threads;

public class Thread100 {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {

            new Thread100Threads(i).start();
        }

    }
}
