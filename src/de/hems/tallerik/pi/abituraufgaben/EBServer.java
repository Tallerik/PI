package de.hems.tallerik.pi.abituraufgaben;

import de.hems.tallerik.pi.echoserver.ServerSocket;
import de.hems.tallerik.pi.echoserver.Socket;

import java.io.IOException;

public class EBServer {
    private ServerSocket server;
    private int port;
    private EBVerwaltung eb;

    public EBServer(int port, EBVerwaltung eb) {
        this.port = port;
        this.eb = eb;
        this.server = null;
    }

    public void startServer() {
        try {
            this.server = new ServerSocket(port);

            while (true) {
                Socket s = server.accept();
                s.write("+OK E-Banking\n");
                String anforderung = s.readLine();
                String[] split = anforderung.split(";");
                String iban = split[1];
                Konto k = this.eb.anmelden(iban, Integer.parseInt(split[2]));
                if(k != null) {
                    s.write("+OK Willkommen\n");
                    anforderung = s.readLine();
                    while (!anforderung.equals("quit")) {
                        split = anforderung.split(";");
                        Konto aK = this.eb.sucheKonto(split[1]);
                        if(aK != null) {
                            boolean ok = this.eb.ueberweisen(k, aK, split[2], Double.parseDouble(split[3]));
                            if(ok) {
                                s.write("+OK Überweisung erfolgreich\n");
                            } else {
                                s.write("-Err Überweisung nicht erfolgt\n");
                            }
                        } else {
                            s.write("-ERR Ungeültige IBAN\n");
                        }
                        double kontostand = k.getKontostand();
                        s.write("Aktuell: "+kontostand+" EUR\n");
                        anforderung = s.readLine();
                    }
                } else {
                    s.write("-ERR Anmeldung fehlgeschlagen\n");
                }
                s.close();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
