package de.hems.tallerik.pi.vererbung;

public class Fuehrungskraft extends Person{
    private double bonus;
    public Fuehrungskraft(String name, String gebdatum, int mgehalt, double bonus) {
        super(name, gebdatum, mgehalt);
        this.bonus = bonus;
    }


    @Override
    public String gruessen() {
        return "Guten Tag liebes Personal";
    }

    @Override
    public int berechneJahresgehalt() {
        return super.berechneJahresgehalt();
    }

    @Override
    public String toString() {
        return "Führungskraft: " + name + " Monatsgehalt: " + monatsgehalt;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
}
