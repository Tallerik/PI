package de.hems.kniffel;

public class Gewinnkarte {

    int[] pasch; // Punktzahlen der jeweiligen Pasche


    public Gewinnkarte() {
        this.pasch = new int[]{0,0,0,0,0,0};
    }

    public void paschgewuerfelt(int augensumme) {
        pasch[augensumme -1] = augensumme * 3;
    }

    public int getGesamtPunkte() {
        int p = 0;
        for (int i = 0; i < pasch.length; i++) {
            p += pasch[i];
        }
        if(p >= 63) {
            p += 35;
        }
        return p;
    }

    public void printKarte() {
        System.out.println("|-------|---|");
        for (int i = 0; i < pasch.length; i++) {
            System.out.println("| Pasch " + (i + 1) + "|" + pasch[i]);
        }
        System.out.println("|-------|---|");
        System.out.println("| Gesamt|" + getGesamtPunkte() + "|");
        System.out.println("|-------|---|");
    }
}
