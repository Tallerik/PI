package de.hems.tallerik.pi.auto;

public class Reifen {

    private double druck;
    private double maxDruck;
    private String marke;
    private double zustand; // x out of 100

    public Reifen(String marke, double maxDruck) {
        this.marke = marke;
        this.maxDruck = maxDruck;
        this.druck = 0.0;
        this.zustand = 100;
    }

    public boolean aufpumpen(double bar) {
        if(zustand <= 20) {
            druck = 0;
            return false;
        }
        if((druck + bar) < maxDruck) {
            druck = druck + bar;
            return true;
        }
        return false;
    }

    public void luftVerlieren(double red) {
        if((druck -red) < 0)
            return;
        druck = druck -red;
    }

    public boolean kannLuftHalten() {
        return zustand <= 20;
    }

    public void abnutzen(double red) {
        zustand = zustand - red;
    }





    public double getDruck() {
        return druck;
    }

    public void setDruck(double druck) {
        this.druck = druck;
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public double getZustand() {
        return zustand;
    }

    public void setZustand(int zustand) {
        this.zustand = zustand;
    }
}