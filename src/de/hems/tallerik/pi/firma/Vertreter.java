package de.hems.tallerik.pi.firma;

import java.util.*;

public class Vertreter {

    private String name;
    private int persNr;
    private Kunde[] kunde;

    public Vertreter(String name) {
        this.name = name;
        kunde = new Kunde[10];
    }

    public boolean hinzufugen(Kunde k) {
        for (int i = 0; i < kunde.length; i++) {

            if(kunde[i] == null) {
                kunde[i] = k;
                return true;
            }

        }
        return false;
    }

    public double berechnekundenumsatze() {
        double d = 0.0;
        for (int i = 0; i < kunde.length; i++) {
            d += kunde[i].getUmsatz();
        }
        return d;
    }

    public boolean entferneKunde(String n) {
        for (int i = 0; i < kunde.length; i++) {
            if(kunde[i].getName() == n) {
                kunde[i] = null;
                return true;
            }
        }
        return false;
    }

    public Kunde[] sortiereUmsatz() {
        Kunde[] k = new Kunde[10];
        List<Kunde> ksort = new ArrayList<Kunde>(Arrays.asList(k));
        ksort.sort(Comparator.comparing(Kunde::getUmsatz));
        for (int i = 0; i < ksort.size(); i++) {
            k[i] = ksort.get(i);
        }
        return k;
    }
}
