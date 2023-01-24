package de.hems.tallerik.pi.abituraufgaben;

import java.util.ArrayList;
import java.util.List;

public class Konto {
    private String iban;
    private int pin;
    private double kontostand;

    private Buchung neuste;

    public Konto(String iban) {
        this.iban = iban;
        this.pin = 0;//EBVerwaltung.generierePin();
        this.kontostand = 0.0;
        this.neuste = null;
    }

    public boolean login(int pin) {return this.pin == pin;}

    public void einzahlen(String text, double betrag) {
        if (betrag <= 0)
            return;

        kontostand += betrag;
        Buchung b = new Buchung(text, betrag);
        hinzufuegenBuchungen(b);
    }

    public boolean ueberweisen(Konto empfaenger, String text, double betrag) {
        if(betrag > this.kontostand)
            return false;
        kontostand -= betrag;
        empfaenger.einzahlen(text, betrag);
        hinzufuegenBuchungen(new Buchung(text, betrag * -1));
        return true;
    }

    private void hinzufuegenBuchungen(Buchung b) {
        if (neuste != null) {
            b.setVorherige(neuste);
        }
        neuste = b;
    }

    public String zeigeBuchung(Buchung b) {
        return b.toString();
    }

    public List<Buchung> sucheBuchungen(String begriff) {

        List<Buchung> list = new ArrayList<>();

        Buchung latest = neuste;
        while (latest != null) {
            if(latest.getText().contains(begriff))
                list.add(latest);
            latest = latest.getVorherige();
        }
        return list;
    }

    public String getIban() {
        return iban;
    }

    public Buchung getNeuste() {
        return neuste;
    }
}
