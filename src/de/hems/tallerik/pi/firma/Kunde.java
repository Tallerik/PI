package de.hems.tallerik.pi.firma;

public class Kunde {

    private String name;
    private boolean istVIP;
    private double umsatz;

    public Kunde(String name) {
        this.name = name;
    }

    public Kunde(String name, boolean istVIP) {
        this.name = name;
        this.istVIP = istVIP;
    }

    public void erhoheUmsatz(double betrag) {
        umsatz += betrag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIstVIP() {
        return istVIP;
    }

    public void setIstVIP(boolean istVIP) {
        this.istVIP = istVIP;
    }

    public double getUmsatz() {
        return umsatz;
    }

    public void setUmsatz(double umsatz) {
        this.umsatz = umsatz;
    }
}
