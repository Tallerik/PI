package de.hems.tallerik.pi.threads;

public class ABCPrinter {
    public static void main(String[] args) {
        new ABCPrinterThread('A',1000);
        new ABCPrinterThread('z',500);
        ABCPrinter.print('8');
    }

    public static void print(char c) {
        System.out.print(c);
    }
}
