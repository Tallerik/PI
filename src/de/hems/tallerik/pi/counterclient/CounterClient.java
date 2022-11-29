package de.hems.tallerik.pi.counterclient;

import java.io.IOException;
import java.util.Scanner;

public class CounterClient {
    private Socket s;

    public CounterClient(String ip, int port) {
        try {
            s = new Socket(ip, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean connect() {
        try {
            boolean suc = s.connect();
            if (!suc)
                return false;
            return s.readLine().equals("CounterServer ready!");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void write(String a) {
        try {
            s.write(a+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean printNext() {
        try {
            if (isConnected()) {

                String data = s.readLine();
                if (data == null)
                    return false;
                System.out.println(data);
                return true;
            }

        } catch (IOException e) {
            return false;
        }
        return false;
    }

    public void close() {
        try {
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        return s.getSocket().isConnected() && !s.getSocket().isClosed();
    }


    public static void main(String[] args) {
        CounterClient cc =new CounterClient("192.168.50.41", 1337);
        System.out.println("Versuche Verbindungsaufbau");
        if(cc.connect()) {
            System.out.println("Verbindung aufgebaut");
        } else {
            System.out.println("Verbindung fehlgeschlagen");
        }
        Scanner sc = new Scanner(System.in);
        while (cc.isConnected()) {
            System.out.print("Counter@Server:~$ ");
            String inp = sc.nextLine();
            if (inp.equals("stopclient"))
                break;
            if (!cc.isConnected())
                break;
            cc.write(inp);
            if(!cc.printNext()) {
                break;
            }
        }
        sc.close();
        cc.close();
        System.out.println("Geschlossen");
    }
}
