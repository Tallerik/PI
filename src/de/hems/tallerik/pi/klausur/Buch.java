package de.hems.tallerik.pi.klausur;

public class Buch {
    String titel;
    String fachbereich;
    double preis;
    int hoehe;
    int breite;

    public Buch(){
        titel = "";
        fachbereich = "";
        double preis = 0.0;
        hoehe = 0;
        breite = 0;
    }
    public Buch(String t, String f, double p, int h, int b) {
        titel = t;
        fachbereich = f;
        preis = p;
        hoehe = h;
        breite = b;
    }

    @Override
    public String toString() {
        return "Buch{" +
                "titel='" + titel + '\'' +
                ", fachbereich='" + fachbereich + '\'' +
                '}';
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getFachbereich() {
        return fachbereich;
    }

    public void setFachbereich(String fachbereich) {
        this.fachbereich = fachbereich;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public int getHoehe() {
        return hoehe;
    }

    public void setHoehe(int hoehe) {
        this.hoehe = hoehe;
    }

    public int getBreite() {
        return breite;
    }

    public void setBreite(int breite) {
        this.breite = breite;
    }
}
