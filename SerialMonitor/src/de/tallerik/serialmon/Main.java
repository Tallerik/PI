package de.tallerik.serialmon;

import com.fazecast.jSerialComm.SerialPort;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static int baud = 9600;
    private static int databits = 8;
    private static int stopbits = 1;
    private static int parity = 0;

    public static void main(String[] args) {
        Serial port = searchArdus();
        if (port == null) {
            System.out.println("Fehler");
            return;
        }
        Monitor m = new Monitor(port);
        m.start();
    }

    private static Serial searchArdus() {
        SerialPort[] serialPorts = SerialPort.getCommPorts();
        List<String> ports = new ArrayList<>();
        List<String> portdesc = new ArrayList<>();
        for (SerialPort sp: serialPorts) {
            ports.add(sp.getSystemPortName());
            portdesc.add(sp.getDescriptivePortName());
        }

        if (ports.size() > 1) {

            System.out.println("Mögliche Ports:");
            for (int i = 0; i < ports.size(); i++) {
                System.out.println(i + " : " + ports.get(i) + ": " + portdesc.get(i));
            }
            Scanner sc = new Scanner(System.in);
            int selection = sc.nextInt();
            sc.close();
            if (selection < ports.size()) {
                System.out.println("Ausgewählt: " + ports.get(selection) + " (" + portdesc.get(selection) + ")");
                return new Serial(ports.get(selection), baud, databits, stopbits, parity);
            } else {
                System.out.println("Auswahl zu hoch");
                return null;
            }
        } else if (ports.size() == 1) {
            return new Serial(ports.get(0), baud, databits, stopbits, parity);
        } else {
            return null;
        }
    }
}