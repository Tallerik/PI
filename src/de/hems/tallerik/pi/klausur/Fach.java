package de.hems.tallerik.pi.klausur;

import java.util.ArrayList;
import java.util.List;

public class Fach {
    String bezeichnung;
    String fachbereich;
    int breite;
    final int HOEHE = 60;
    Regal regal;

    List<Buch> buecher;

    public Fach(String f, int breite, String bezeichnung) {
        this.bezeichnung = bezeichnung;
        fachbereich = f;
        this.breite = breite;
        regal = null;
        buecher = new ArrayList<>();
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getFachbereich() {
        return fachbereich;
    }

    public void setFachbereich(String fachbereich) {
        this.fachbereich = fachbereich;
    }

    public int getBreite() {
        return breite;
    }

    public void setBreite(int breite) {
        this.breite = breite;
    }

    public int getHOEHE() {
        return HOEHE;
    }

    public Regal getRegal() {
        return regal;
    }

    public void setRegal(Regal regal) {
        this.regal = regal;
    }

    public List<Buch> getBuecher() {
        return buecher;
    }

    public void setBuecher(List<Buch> buecher) {
        this.buecher = buecher;
    }
}
