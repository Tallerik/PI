package de.tallerik.serialmon;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Monitor {

    private Serial s;
    private ListenerThread thread;

    public Monitor(Serial s) {
        this.s = s;
    }

    public void start() {
        thread = new ListenerThread(s);
        thread.start();

        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                String data = sc.nextLine();
                if(data.equals("stop")) {
                    break;
                }
                s.write(data);
            } catch (NoSuchElementException e) {}

        }
        sc.close();
        thread.setRunning(false);
    }
}
