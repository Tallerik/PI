package de.hems.tallerik.pi.threads;

public class ABCPrinterThread extends Thread {
    private char c;
    private int ms;
    public ABCPrinterThread (char c, int
            ms){
        this.c = c;
        this.ms = ms;
        this.start();
    }
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.print(c);
            try {
                Thread.sleep(ms); // 1000 ms = 1 s
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}