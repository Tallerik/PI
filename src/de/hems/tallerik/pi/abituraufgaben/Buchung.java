package de.hems.tallerik.pi.abituraufgaben;

import java.util.Date;

public class Buchung {
    private Date buTag;
    private String text;
    private double betrag;

    private Buchung vorherige;

    public Buchung(String text, double betrag) {
        this.text = text;
        this.betrag = betrag;
        this.buTag = new Date();
        this.vorherige = null;
    }

    public String getText() {
        return text;
    }

    public void setVorherige(Buchung vorherige) {
        this.vorherige = vorherige;
    }

    public Buchung getVorherige() {
        return vorherige;
    }

    @Override
    public String toString() {
        return "Buchungstag: " + buTag + ", Buchungstext: " + text + " Betrag in EUR: " + betrag;
    }
}
