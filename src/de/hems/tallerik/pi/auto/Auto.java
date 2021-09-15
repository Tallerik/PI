package de.hems.tallerik.pi.auto;

import java.util.ArrayList;
import java.util.List;

public class Auto {
    // Klassenatribut
    private static int iterator = 1;

  // Anfang Attribute
    private final double LUFTVERLUST = 0.000167; // Luftverlust der Reifen in bar pro Km
    private final double REIFENABRIEB = 0.002; // Reifenabnutzung von 100 pro Km

    private int fin;
    private String marke;
    private String modell;
    private int baujahr;
    private int tueren;
    private float leistung;
    private float drehmoment;
    private List<Reifen> raeder;
    private int sitzPlaetze;
    private List<String> features;
    private double tankinhalt;
    private double tankvolumen;
    private String kraftstoffArt;
    private double verbrauch;
    private double empfohlenerReifenLuftdruck;
    private double kilometerstand;
  // Ende Attribute


    public Auto(String marke, String modell, int baujahr) {
        fin = iterator;
        iterator++;
        this.marke = marke;
        this.modell = modell;
        this.baujahr = baujahr;
        this.features = new ArrayList<>();
        this.tueren = 0;
        this.raeder = new ArrayList<>();
        this.tankvolumen = 0;
        this.kraftstoffArt = "Unbekannt";
        this.verbrauch = 0;
        this.leistung = 0.0f;
        this.drehmoment = 0.0f;
        this.sitzPlaetze = 0;
        this.empfohlenerReifenLuftdruck = 0;
        this.kilometerstand = 0.0;
    }

    public Auto(String marke, String modell, int baujahr, int tueren, float leistung, float drehmoment, List<Reifen> raeder,
                int sitzPlaetze, List<String> features, double tankinhalt, double tankvolumen, String kraftstoffArt,
                double verbrauch, double empfohlenerReifenLuftdruck, double kilometerstand) {
        fin = iterator;
        iterator++;
        this.marke = marke;
        this.modell = modell;
        this.baujahr = baujahr;
        this.tueren = tueren;
        this.leistung = leistung;
        this.drehmoment = drehmoment;
        this.raeder = raeder;
        this.sitzPlaetze = sitzPlaetze;
        this.features = features;
        this.tankinhalt = tankinhalt;
        this.tankvolumen = tankvolumen;
        this.kraftstoffArt = kraftstoffArt;
        this.verbrauch = verbrauch;
        this.empfohlenerReifenLuftdruck = empfohlenerReifenLuftdruck;
        this.kilometerstand = kilometerstand;
    }
  // Anfang Methoden

    /**
     * Versucht das Auto, um die gegebene anzahl zu tanken
     *
     * @param volumen          Gewuenschtes Tank Volumen
     * @param luftdruckPruefen bestimmt, ob der Luftdruck geprueft werden soll
     * @return true, wenn getankt wurde, false, wenn nicht
     */
    public boolean tanken(int volumen, boolean luftdruckPruefen) {
        if (tankinhalt + volumen > tankvolumen) {
            return false;
        }
        tankinhalt = tankvolumen + volumen;

        if(luftdruckPruefen){
            reifenAufpumpen(this.empfohlenerReifenLuftdruck);
        }

        return true;
    }

    public double volltanken() {
        double dif = tankvolumen - tankinhalt;
        tankinhalt = tankvolumen;
        return dif;
    }

    public boolean reifenAufpumpen(double druck){

        for(Reifen r: raeder){
            if (!r.aufpumpen(druck)) {
                return false;
            }
        }
        return false;
    }

    public void addFeature(String feature) {
        features.add(feature);
    }

    public void addRad(Reifen r) {
        raeder.add(r);
    }

    public List<String> getFeatures() {
        return features;
    }


    /**
     * Wechselt den Reifen an einem Auto.
     *
     * @param slot Nr des zu wechselnden Reifen
     * @param neu  Neuer Reifen
     * @return wenn Reifenwechsel erfolgreich: den Alten Reifen. Sonst: Null
     */
    public Reifen reifenwechseln(int slot, Reifen neu) {
        if(raeder.get(slot) != null) {
            Reifen old = raeder.get(slot);
            raeder.set(slot, neu);
            return old;
        }
        return null;
    }

    public boolean fahren(int km) {
        double benoetigterKraftstoff = (km / 100.0) * this.verbrauch;
        if (benoetigterKraftstoff > this.tankinhalt) {
            return false;
        }
        this.tankinhalt = this.tankinhalt - benoetigterKraftstoff;
        this.raeder.forEach((reifen) -> {
            reifen.luftVerlieren(LUFTVERLUST * Math.random() * 10 * km); // Luftverlust wird zufällig mit Zahlen zwischen 0 und 10 Multipliziert.
            double extraAbtrieb = this.empfohlenerReifenLuftdruck - reifen.getDruck();
            reifen.abnutzen(REIFENABRIEB * extraAbtrieb *  km); // Reifen wird mehr abgenutzt wenn Luftdruck zu wenig.
        });
        kilometerstand += km;
        return true;

    }

    public double kalkuliereReichweite() {
        // km / 100 * verbrauch = Reichweite | * 100
        // verbrauch * km = Reichweite * 100 |
        //
        return 2.0;
    }

    @Override
    public String toString() {
        String s = marke + " " + modell + " " + baujahr + "\n\n";
        s += "Tueren: " + tueren + "\n";
        s += "Leistung: " + leistung + "PS\n";
        s += "Drehmoment: " + drehmoment + "Nm\n";
        s += "Sitz Plaetze: " + sitzPlaetze + "\n";
        s += "Kilometerstand: " + kilometerstand + "\n";
        s += "Tank Volumen: " + tankvolumen + "L\n";
        s += "Tueren: " + tueren + "\n";
        s += "Kraftstoff art: " + kraftstoffArt + "\n";
        s += "Verbrauch: " + verbrauch + " L/100Km\n";
        s += "Raeder: " + raeder.size() + "\n";
        s += "Features: \n";

        for(String f: features) {
            s += " - " + f + "\n";
        }
        return s;
    }

    public int getFin() {
        return fin;
    }

    public double getLUFTVERLUST() {
        return LUFTVERLUST;
    }

    public double getREIFENABRIEB() {
        return REIFENABRIEB;
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public int getBaujahr() {
        return baujahr;
    }

    public void setBaujahr(int baujahr) {
        this.baujahr = baujahr;
    }

    public int getTueren() {
        return tueren;
    }

    public void setTueren(int tueren) {
        this.tueren = tueren;
    }

    public float getLeistung() {
        return leistung;
    }

    public void setLeistung(float leistung) {
        this.leistung = leistung;
    }

    public float getDrehmoment() {
        return drehmoment;
    }

    public void setDrehmoment(float drehmoment) {
        this.drehmoment = drehmoment;
    }

    public List<Reifen> getRaeder() {
        return raeder;
    }

    public void setRaeder(List<Reifen> raeder) {
        this.raeder = raeder;
    }

    public int getSitzPlaetze() {
        return sitzPlaetze;
    }

    public void setSitzPlaetze(int sitzPlaetze) {
        this.sitzPlaetze = sitzPlaetze;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public double getTankinhalt() {
        return tankinhalt;
    }

    public void setTankinhalt(double tankinhalt) {
        this.tankinhalt = tankinhalt;
    }

    public double getTankvolumen() {
        return tankvolumen;
    }

    public void setTankvolumen(double tankvolumen) {
        this.tankvolumen = tankvolumen;
    }

    public String getKraftstoffArt() {
        return kraftstoffArt;
    }

    public void setKraftstoffArt(String kraftstoffArt) {
        this.kraftstoffArt = kraftstoffArt;
    }

    public double getVerbrauch() {
        return verbrauch;
    }

    public void setVerbrauch(double verbrauch) {
        this.verbrauch = verbrauch;
    }

    public double getEmpfohlenerReifenLuftdruck() {
        return empfohlenerReifenLuftdruck;
    }

    public void setEmpfohlenerReifenLuftdruck(double empfohlenerReifenLuftdruck) {
        this.empfohlenerReifenLuftdruck = empfohlenerReifenLuftdruck;
    }
  // Ende Methoden
}
