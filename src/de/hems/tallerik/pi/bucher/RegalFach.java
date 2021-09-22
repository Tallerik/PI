package de.hems.tallerik.pi.bucher;

import java.util.List;

public class RegalFach {

    private String name;
    private double hohe;
    private double breite;
    private double tiefe;
    private List<Buch> bucher;
    // Ende Attribute


    public RegalFach(String name, double hohe, double breite, double tiefe) {
        this.name = name;
        this.hohe = hohe;
        this.breite = breite;
        this.tiefe = tiefe;
    }

    public boolean canAddBook(Buch buch) {
        if(buch.getDepth() > tiefe || buch.getHeight() > hohe) {
            return false;
        }
        double existsbooks = bucher.stream().mapToDouble(Buch::getWidth).sum();


        if((breite - existsbooks) >= buch.getWidth()) {
            return true;
        }
        return false;
    }

    // Anfang Methoden
    public String getName() {
        return name;
    }

    public void setName(String nameNeu) {
        name = nameNeu;
    }

    public double getHohe() {
        return hohe;
    }

    public void setHohe(double hoheNeu) {
        hohe = hoheNeu;
    }

    public double getBreite() {
        return breite;
    }

    public void setBreite(double breiteNeu) {
        breite = breiteNeu;
    }

    public double getTiefe() {
        return tiefe;
    }

    public void setTiefe(double tiefeNeu) {
        tiefe = tiefeNeu;
    }

    public List<Buch> getBucher() {
        return bucher;
    }

    public void setBucher(List<Buch> bucherNeu) {
        bucher = bucherNeu;
    }

    public void addBook(Buch buch) {
        if(canAddBook(buch)) {
            bucher.add(buch);
        }
    }

    @Override
    public String toString() {
        String s = "Fach mit folgenden Büchern: ";
        for (Buch b : bucher) {
            s = s + b.getTitel() + ", ";
        }
        return s;
    }
}
