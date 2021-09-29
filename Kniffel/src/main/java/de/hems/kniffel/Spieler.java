package de.hems.kniffel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Spieler {
    private String name;
    private Gewinnkarte karte;
    private Becher becher;
    private int rundensiege;

    public Spieler(String name) {
        this.name = name;
        this.karte = new Gewinnkarte();
        this.becher = new Becher();
        rundensiege = 0;
    }


    public void spielzug() {
        Scanner sc = new Scanner(System.in);
        System.out.println(name + " ist an der Reihe");
        karte.printKarte();
        System.out.println("Es wird gewürfelt....");
        System.out.println(wurfelformat(becher.wuerfeln()));
        for (int s = 0; s < 2; s++) {
            System.out.println("Welche Würfel möchtest du nochmal Würfeln? 2,4 | keine");
            String in = sc.nextLine();
            if(!in.equalsIgnoreCase("keine")) {
                String[] w = in.split(",");
                int[] ints = new int[3];

                for (int i = 0; i < w.length; i++) {
                    try {
                        for (int j = 0; j < ints.length; j++) {
                            ints[j] = Integer.parseInt(w[i]);
                        }
                    } catch (NumberFormatException e) {}
                }

                System.out.println(wurfelformat(becher.wuerfeln(ints)));
            }
        }

        try {
            System.out.println("In welchen Pasch möchtest du einsetzen?");
            int p = Integer.parseInt(sc.nextLine());
            int count = 0;
            for (int i = 0; i < becher.getWurfel().length; i++) {
                if(becher.getWurfel()[i] == p) {
                    count++;
                }
            }
            karte.paschgewuerfelt(p, count);
            karte.printKarte();
            System.out.println("Zug beendet");

        } catch (NumberFormatException e) {
            System.out.println("Fehlerhaft");
        }
    }


    private String wurfelformat(int[] wurfel) {
        // pls dont kill me for this
        return "Deine Würfel: " +
                wurfel[0] + ", " +
                wurfel[1] + ", " +
                wurfel[2] + ", " +
                wurfel[3] + ", " +
                wurfel[4];
    }

    public void gewonnen() {
        rundensiege++;
    }
    public void resetGewinnkarte() {
        karte = new Gewinnkarte();
    }

    public Gewinnkarte getGewinnKarte() {
        return karte;
    }

    public String getName() {
        return name;
    }

    public int getRundensiege() {
        return rundensiege;
    }
}
