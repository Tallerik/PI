package de.hems.tallerik.pi.firma;

public class Werk {

  // Anfang Attribute
    private Produkt produkt;
    private String bezeichnung;
    private double umsatz;
  // Ende Attribute

    public Werk(String bezeichnung) {
        this.bezeichnung = bezeichnung;
        umsatz = 0.0;
    }
  // Anfang Methoden

    public double berechneumsatz() {
        umsatz = produkt.getPreis() * produkt.getAnzahl();
        return produkt.getPreis() * produkt.getAnzahl();
    }

    public void einstellenproduktion() {
        //TODO KP
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public double getUmsatz() {
        return umsatz;
    }

    public void setUmsatz(double umsatz) {
        this.umsatz = umsatz;
    }
  // Ende Methoden
}
