package de.tallerik.serialmon;

public class ListenerThread extends Thread {

    private Serial port;
    private boolean running = true;

    public ListenerThread(Serial p) {
        port = p;
    }

    @Override
    public void run() {
        System.out.println("Thread startet");
        while (running) {
            String data = port.readLine();
            if (data != null) {
                System.out.println(data);
            }
        }
        System.out.println("Thread stopped");
    }


    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }
}
