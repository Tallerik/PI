package de.hems.tallerik.pi.threads;

public class Thread100Threads extends Thread {
    private int number;

    public Thread100Threads(int n) {
        number = n;
    }

    @Override
    public void run() {
        while (true) {

            System.out.println("Thread " + number);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
