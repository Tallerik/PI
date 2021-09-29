package de.hems.kniffel;

import java.util.Scanner;

public class Kniffel {


    Spieler sp1;
    Spieler sp2;

    public Kniffel() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Wie heist der erste Spieler?");
        sp1 = new Spieler(sc.nextLine());
        System.out.println("Wie heist der zweite Spieler?");
        sp2 = new Spieler(sc.nextLine());

        System.out.println("Das Spiel beginnt");
        for (int i = 0; i < 3; i++) {
            runde();
            System.out.println("Runde abgeschlossen\n\n");
        }
        System.out.println("Spiel beendet. Siege:");
        System.out.println(sp1.getName() + ": " + sp1.getRundensiege() + " Siege");
        System.out.println(sp2.getName() + ": " + sp2.getRundensiege() + " Siege");

        System.out.println("c u");

    }


    private void runde() {
        System.out.println("Die Runde beginnt.");
        for (int i = 0; i < 5; i++) {
            clearTerm();
            sp1.spielzug();
            clearTerm();
            sp2.spielzug();
        }

        System.out.println("Runde beendet");

        if(sp1.getGewinnKarte().getGesamtPunkte() > sp2.getGewinnKarte().getGesamtPunkte()) {
            System.out.println(sp1.getName() + " hat die Runde gewonnen");
            sp1.gewonnen();
        } else {
            System.out.println(sp2.getName() + " hat die Runde gewonnen");
            sp2.gewonnen();
        }

        sp1.resetGewinnkarte();
        sp2.resetGewinnkarte();

    }

    private void clearTerm() {
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }


}
