package de.hems.kniffel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kniffel {
    /**
     * Debug-Mode
     * Runs Game only with one Player as AI and 1 round.
     *
     */
    private final boolean DEBUG = false;
    private final int DEBUG_AI = 1;

    /**
     * Spieler des Spiels
     */
    private List<Spieler> spieler;
    private Scanner sc;

    /**
     * Konstruktor des Spiels. Wird beim Programmstart ausgeführt
     */
    public Kniffel() {
        int runden;
        spieler = new ArrayList<>();
        sc = new Scanner(System.in);
        if(!DEBUG) {
            // Einlesen von custom informationen
            addSpieler();

            System.out.println("Wie viele Runden möchtest du Spielen?");
            runden = sc.nextInt();
        } else {
            runden = 1;
            spieler.add(new Spieler("BOT", DEBUG_AI));
        }



        // Spiel beginnt

        System.out.println("Das Spiel beginnt");
        for (int i = 0; i < runden; i++) {
            runde();
            System.out.println("Runde abgeschlossen\n\n");
        }

        // Ende
        System.out.println("Spiel beendet. Siege:");
        for (Spieler sp : spieler) {
            System.out.println(sp.getName() + ": " + sp.getRundensiege() + " Siege");
        }
        System.out.println("c u");

        sc.close();
    }


    private void addSpieler() {
        System.out.println("Wie heist der Spieler?");
        String name = sc.nextLine();
        String ai;
        do {
            System.out.println("Soll der Spieler vom Computer gespielt werden? [j/n]");
            ai = sc.nextLine();
        } while (!ai.equals("j") && !ai.equals("n"));
        boolean isAI = ai.equals("j");
        if(isAI) {
            int diff;
            do {
                System.out.println("Welche AI soll verwendet werden [0/1]");
                diff = sc.nextInt();
            } while (diff != 0 && diff != 1);

            spieler.add(new Spieler(name, diff));
        } else {
            spieler.add(new Spieler(name));
        }


        String neu;
        do { //TODO: BUG: MESSAGE PRINTING TWICE WHEN AI IS SELECTED
            System.out.println("Möchtest du einen weiteren Spieler hinzufügen? [j/n]");
            neu = sc.nextLine();
        } while (!neu.equals("j") && !neu.equals("n"));

        if(neu.equals("j")) {

            addSpieler();
        }
    }

    /**
     * Eine Spielrunde
     */
    private void runde() {
        System.out.println("Die Runde beginnt.");

        // Spielzüge beider Spieler
        for (int i = 0; i < 6; i++) {
            for (Spieler sp : spieler) {
                clearTerm();
                sp.spielzug();
            }
        }

        System.out.println("Runde beendet");

        // Runde beendet, der Sieger steht fest
        Spieler bestPlayer = null;
        int bestpt = 0;
        for (Spieler sp : spieler) {
            if(sp.getGewinnKarte().getGesamtPunkte() > bestpt) {
                bestPlayer = sp;
                bestpt = sp.getGewinnKarte().getGesamtPunkte();
            }
        }
        if(bestPlayer != null) {
            System.out.println(bestPlayer.getName() + " hat die Runde mit "+ bestpt +" gewonnen");
            bestPlayer.gewonnen();
        }

        for (Spieler sp : spieler) {
            sp.resetGewinnkarte();
        }

    }

    private void clearTerm() {
        for (int i = 0; i < 5; i++) {
            System.out.println();
        }
    }


}
