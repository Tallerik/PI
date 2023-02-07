package de.hems.tallerik.wetter.client;

import de.hems.tallerik.wetter.client.lib.DataSet;
import de.hems.tallerik.wetter.client.lib.Socket;

import java.io.IOException;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 1337);
        s.connect();
        System.out.println("Verbunden");
        DataSet d = new DataSet(new Date(), 3.5, 23, 2, 33, 12.2);
        System.out.println(d);
        s.write("add:"+d.toEncodedString()+"\n");
        System.out.println("Gesendet");
        s.write("last");
        System.out.println(new DataSet(s.readLine()));

    }
}