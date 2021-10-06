package de.hems.kniffel;

/**
 * Gewinnkarte; Representiert die entsprechenden Felder auf der Gewinnkarte
 */
public class Gewinnkarte {

    /**
     * Speichert, welche Pasche bereits eingesetzt wurde.
     */
    private int[] pasch; // Punktzahlen der jeweiligen Pasche


    /**
     * Konstruktor; pasch wird mit Standardwerten initialisiert
     */
    public Gewinnkarte() {
        this.pasch = new int[]{0,0,0,0,0,0};
    }

    /**
     * Wird aufgerufen, wenn ein Pasch erzielt wurde und in die Karte eingesetzt werden muss
     *
     * @param augensumme Augensummen des Pasches
     * @param anzahl     Anzahl der Würfel mit entsprechender Augensumme
     */
    public void paschgewuerfelt(int augensumme, int anzahl) {
        try {

            pasch[augensumme -1] = augensumme * anzahl;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Indexfehler @ Gewinnkarte#paschgewuerfelt");
        }
    }

    /**
     * Zählt die Gesamtpunkte der Gewinnkarte zusammen.
     * Wenn die Punktzahl höher als 63 ist, bekommt der Spieler 35 Zusatzpunkte
     *
     * @return Gesamtpunkte
     */
    public int getGesamtPunkte() {
        int p = 0;
        for (int j : pasch) {
            p += j;
        }
        if(p >= 63) {
            p += 35;
        }
        return p;
    }

    /**
     *
     * @param index der Index der gewünschten Punkte (0 Based).
     * @return die Punktanzahl
     */
    public int getPunkte(int index){
        return this.pasch[index];
    }
    public int getNumber(int index){
        return this.pasch[index] / (index +  1);
    }

    public int[] getPasch(){
        return this.pasch;
    }

    /**
     * Gibt die Gewinnkarte Kommandozeilengerecht aus.
     */
    public void printKarte() {
        System.out.println("|--------|---|");
        System.out.println("|Gewinnkarte |");
        System.out.println("|--------|---|");
        for (int i = 0; i < pasch.length; i++) {
            System.out.println("| Pasch " + (i + 1) + "|" + pasch[i]);
        }
        System.out.println("|--------|---|");
        System.out.println("| Gesamt |" + getGesamtPunkte());
        System.out.println("|--------|---|");
    }
}
