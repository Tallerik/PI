package de.hems.tallerik.pi.vererbung;

public class Angestellter extends Person{

    private boolean istAussendienst;
    private int kuendigungsfrist;

    public Angestellter(String name, int mgehalt, boolean isAussendienst, int kuendigungsfrist) {
        super(name, "", mgehalt);
        this.istAussendienst = isAussendienst;
        this.kuendigungsfrist = kuendigungsfrist;
    }

    @Override
    public String gruessen() {
        return super.gruessen();
    }

    @Override
    public int berechneJahresgehalt() {
        return super.berechneJahresgehalt();
    }

    @Override
    public String toString() {
        return "Angestellter: " + getName() + " Monatsgehalt: " + monatsgehalt;
    }


    public boolean isIstAussendienst() {
        return istAussendienst;
    }

    public void setIstAussendienst(boolean istAussendienst) {
        this.istAussendienst = istAussendienst;
    }

    public int getKuendigungsfrist() {
        return kuendigungsfrist;
    }

    public void setKuendigungsfrist(int kuendigungsfrist) {
        this.kuendigungsfrist = kuendigungsfrist;
    }
}
