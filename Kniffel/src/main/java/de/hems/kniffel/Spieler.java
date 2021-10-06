package de.hems.kniffel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Klasse Spieler.
 */
public class Spieler {
    // Felder der Klasse
    private String name;
    private Gewinnkarte karte;
    private Becher becher;
    private int rundensiege;
    private boolean isAI; // Ist der Spieler AI gesteuert
    private int aiSkill; // Welche AI verwendet werden soll

    /**
     * Initialisiert einen neuen Spieler ohne AI
     * @param name Name des Spielers
     */
    public Spieler(String name) {
        this.name = name;
        this.karte = new Gewinnkarte();
        this.becher = new Becher();
        this.isAI = false;
        this.aiSkill = 0;
        rundensiege = 0;
    }

    /**
     * Initialisiert einen neuen Spieler mit AI
     * @param name Name des Spielers
     * @param skill Welche AI verwendet werden soll (0 oder 1)
     */
    public Spieler(String name, int skill) {
        this.name = name;
        this.karte = new Gewinnkarte();
        this.becher = new Becher();
        this.isAI = true;
        this.aiSkill = skill;
        rundensiege = 0;
    }

    /**
     * Ein Spielzug des Spielers
     */
    public void spielzug() {
        // Wenn der Spieler AI gesteuert ist, wird der Spielzug von der AI fortgesetzt
        if(isAI) {
            switch (this.aiSkill){
                case 0: {
                    aiSpielzug_0();
                    break;
                }
                case 1: {
                    aiSpielzug_1();
                    break;
                }
            }
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println(name + " ist an der Reihe");
        karte.printKarte();
        System.out.println("Es wird gewürfelt....");
        System.out.println(wurfelformat(becher.wuerfeln()));


        // Neu Würfeln
        for (int s = 0; s < 2; s++) {
            System.out.println("Welche Würfel möchtest du nochmal Würfeln? 2,4 | keine");
            String in = sc.nextLine();
            if(!in.equalsIgnoreCase("keine")) {
                String[] w = in.split(",");
                if(w.length > 3){
                    System.out.println("Nein");
                    return;
                } else {
                    int[] ints = new int[w.length];

                    for (int i = 0; i < w.length; i++) {
                        try {
                            for (int j = 0; j < ints.length; j++) {
                                if(ints[j] == 0) {
                                    ints[j] = Integer.parseInt(w[i]);
                                    break;
                                }

                            }
                        } catch (NumberFormatException e) {}
                    }

                    System.out.println(wurfelformat(becher.wuerfeln(ints)));
                }
            }
        }


        // Einsetzen in das entsprechende Feld für den Pasch
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


    /**
     * Spielzug von der AI.
     */
    private void aiSpielzug_0() {
        System.out.println(name + " ist an der Reihe");
        karte.printKarte();
        System.out.println("Es wird gewürfelt....");
        int[] wuerfel = becher.wuerfeln();
        System.out.println(wurfelformat(wuerfel));

        // Welchen Pasch wir verfolgen werden
        int gewollterPasch = 0;
        int[] eachCount = new int[6];
        // wir können zwei mal mit je drei würfeln neu würfeln
        for (int s = 0; s < 2; s++) {
            // wie viele von gewollterPasch wir haben
            int highestPasch = 0;
            gewollterPasch = 0;
            List <Integer> rerolls = new ArrayList<Integer>();
            eachCount = new int[6];
            for (int i = 0; i < wuerfel.length; i++){
                eachCount[wuerfel[i] - 1]++;
            }
            for (int i = 0; i < eachCount.length; i++){
                // Berechnet welchen Pasch wir verfolgen, wenn nötig basierend auf der Gewinnkarte
                if(eachCount[i] > highestPasch) {
                    highestPasch = eachCount[i];
                    gewollterPasch = i;
                }
                if(eachCount[i] == highestPasch){
                    if(karte.getPunkte(i) > karte.getPunkte(highestPasch)){
                        gewollterPasch = i;
                    }
                }
            }
            for(int i = 0; i < wuerfel.length; i++){
                // Zuerst werden alle würfel die wir nur einmal haben neu gewürfelt
                if(rerolls.size() >= 3){
                    break;
                }
                if(eachCount[wuerfel[i] - 1] == 1){
                    rerolls.add(i);
                }
            }
            if(!(rerolls.size() >= 3)){
                for(int i = 0; i < wuerfel.length; i++){
                    // als nächstes alles andere was wir nicht brauchen
                    if(rerolls.size() >= 3){
                        break;
                    }
                    if(wuerfel[i] != gewollterPasch){
                        rerolls.add(i);
                    }
                }
            }

            int[] rerollArray = new int[rerolls.size()];
            for(int i = 0; i < rerollArray.length; i++){
                rerollArray[i] = rerolls.get(i);
            }
            if(rerollArray.length == 0){
                System.out.println("Es werden keine Würfel erneut gewürfelt");
            }else {

                System.out.println("Folgende Würfel werden erneut gewürfelt");
                System.out.println(wurfelformat(rerollArray));
                becher.wuerfeln(rerollArray);
                System.out.println("Es wird gewürfelt....");
                System.out.println(wurfelformat(wuerfel));
            }
        }
        //Insert Pasch
        System.out.print("Nimmt Pasch: ");
        System.out.println(gewollterPasch + 1);
        karte.paschgewuerfelt(gewollterPasch + 1, eachCount[gewollterPasch]);

    }

    private void aiSpielzug_1(){
        System.out.println(name + " ist an der Reihe");
        karte.printKarte();
        System.out.println("Es wird gewürfelt....");
        int[] wuerfel = becher.wuerfeln();
        System.out.println(wurfelformat(wuerfel));

        /* Gewollten Pasch berechnen */
        // verfolgter wert 0 - 5
        int verfolgterPasch = -1;
        // Verfolgte Pasche 0 - 5 als würfelaugen
        List<Integer> verfolgtePasche = new ArrayList<Integer>();
        List<Integer> tier1Pasch = new ArrayList<Integer>();
        List<Integer> tier2Pasch = new ArrayList<Integer>();

        // Speichert wie viele Würfel wir pro zahl haben
        int[] xVonZahl = new int[6];

        // Der aktuelle Punktestand
        int[] aktuellePunkte = karte.getPasch();
        for(int i = 0; i < aktuellePunkte.length; i++){
            if(aktuellePunkte[i] == 0){
                tier1Pasch.add(i);
            }
        }

        for(int z = 0; z < 2; z++){
            List<Integer> rerolls = new ArrayList<Integer>();

            //zwei Rerolls...
            for(int i = 0; i < wuerfel.length; i++){
                xVonZahl[wuerfel[i] -1]++;
            }
            // Packt realistische pasche in verfolgtePasche
            for(int i = 0; i < tier1Pasch.size(); i++){
                if(xVonZahl[tier1Pasch.get(i)] > 1){
                    verfolgterPasch = tier1Pasch.get(i);
                }
            }
            // wenn elemente in verfolgte Pasche sind, berechne das beste
            if(verfolgterPasch == -1){
                //tier2 pasche nutzen
                for(int i = 0; i < aktuellePunkte.length; i++){
                    if(karte.getNumber(i) < 3 && xVonZahl[i] > 1){
                        tier2Pasch.add(i);
                    }
                }
                if(tier2Pasch.size() > 0){
                        verfolgterPasch = tier2Pasch.get(tier2Pasch.size() -1);
                }else{
                // Grösste nr
                int highestPasch = 0;

                // Berechnet welchen Pasch wir verfolgen, wenn nötig basierend auf der Gewinnkarte
                for(int i = 0; i < wuerfel.length; i++){
                    if(xVonZahl[i] > highestPasch) {
                            highestPasch = xVonZahl[i];
                            verfolgterPasch = i;
                            }
                    if(xVonZahl[i] == highestPasch){
                        if(karte.getPunkte(i) > karte.getPunkte(highestPasch)){
                            verfolgterPasch = i;
                        }
                    }
                }
                }
            }
            // reroll based on pasch
            // reroll ones
            for(int i = 0; i < wuerfel.length; i++){
                if(rerolls.size() >= 3){
                    break;
                }
                if(xVonZahl[wuerfel[i] -1] == 1){
                    rerolls.add(i);
                }
            }
            // reroll everything else
            if(!(rerolls.size() >= 3)){
                for(int i = 0; i < wuerfel.length; i++){
                    if(rerolls.size() >= 3){
                        break;
                    }
                    if((wuerfel[i] -1) != verfolgterPasch){
                        if(!rerolls.contains(i)){
                            rerolls.add(i);
                        }
                    }
                }
            }




            int[] rerollArray = new int[rerolls.size()];
            for(int i = 0; i < rerollArray.length; i++){
                rerollArray[i] = rerolls.get(i);
            }
            if(rerollArray.length == 0){
                System.out.println("Es werden keine Würfel erneut gewürfelt");
            }else {

                System.out.println("Folgende Würfel werden erneut gewürfelt");
                System.out.println(wurfelformat(rerollArray));
                becher.wuerfeln(rerollArray);
                System.out.println("Es wird gewürfelt....");
                System.out.println(wurfelformat(wuerfel));
            }

            
        } // end reroll

        //Insert Pasch
        System.out.print("Nimmt Pasch: ");
        System.out.println(verfolgterPasch + 1);
        karte.paschgewuerfelt(verfolgterPasch + 1, xVonZahl[verfolgterPasch]);

    }

    /**
     * Kommandozeilenfreundliche Ausgabe der Würfel
     * @param wurfel Würfel, welche ausgegeben werden sollen
     * @return Formatierte Ausgabe der Würfel
     */
    private String wurfelformat(int[] wurfel) {
        String s = "";
        for(int i = 0; i < wurfel.length; i++){
            s += wurfel[i];
            if(!(i == wurfel.length -1)){
                s += ", ";
            }

        }
        return s;
    }

    /**
     * Spieler hat gewonnen.
     * Rundensiege werden um 1 erhöht
     */
    public void gewonnen() {
        rundensiege++;
    }

    /**
     * Gewinnkarte neu erstellen.
     * Wird am Rundenende, nach berechnung der Punkte ausgeführt
     */
    public void resetGewinnkarte() {
        karte = new Gewinnkarte();
    }

    /**
     * Getter der Gewinnkarte
     * @return Gewinnkarte
     */
    public Gewinnkarte getGewinnKarte() {
        return karte;
    }

    /**
     * Getter des Namens
     * @return Name des Spielers
     */
    public String getName() {
        return name;
    }

    /**
     * Getter der Rundensiege
     * @return Rundensiege
     */
    public int getRundensiege() {
        return rundensiege;
    }
}
