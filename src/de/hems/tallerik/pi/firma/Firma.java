package de.hems.tallerik.pi.firma;

public class Firma {
    private String firmenname;
    private double gesamtumsatz;
    private Werk[] werke;
    private Vertreter[] vertreter;

    public Firma(String n) {
        this.firmenname = n;
        this.gesamtumsatz = 0.0;
        this.werke = new Werk[10];
        this.vertreter = new Vertreter[10];
    }

    public double berechnegesamtumsatz() {
        double d = 0.0;
        for (int i = 0; i < werke.length; i++) {
            d += werke[i].berechneumsatz();
        }
        return 0.0; //Umsatz aller Werke
    }

    public String steuereproduktion() {
        return ""; //TODO: Kein Plan
    }

    public int hinzufuegen(Werk w) {
        for (int i = 0; i < werke.length; i++) {
            if(werke[i] == null) {
                werke[i] = w;
                return 0;
            }
        }
        return 1; // Was das für ein INt? TEMP 0 für erfolg; 1 für fehler
    }

    @Override
    public String toString() {
        return "Firma: " + firmenname;
    }
}
