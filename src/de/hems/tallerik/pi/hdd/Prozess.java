package de.hems.tallerik.pi.hdd;

public class Prozess {

    private String name;
    private boolean laufend;
    private boolean bereit;
    private int prio;

    public Prozess() {
        this.name = "Unbenannter Prozess";
        this.laufend = false;
        this.bereit = false;
        this.prio = 0;
    }
    public Prozess(String n, boolean b) {
        this.name = n;
        this.bereit = b;
        this.laufend = false;
        this.prio = 0;
    }
    public Prozess(String n, boolean l, boolean b, int p) {
        this.name = n;
        this.laufend = l;
        this.bereit = b;
        this.prio = p;
    }

    // Beispielimplementierung
    public static void main(String[] args) {

        Prozess p = new Prozess("Messwerte_aufnehmen", false, true, 2);
        System.out.println(p.toString());

    }

    public void setBereit(boolean bereit) {
        this.bereit = bereit;
    }

    public void setLaufend(boolean laufend) {
        this.laufend = laufend;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrio(int prio) {
        this.prio = prio;
    }

    public int getPrio() {
        return prio;
    }

    public String getName() {
        return name;
    }

    public boolean getBereit() {
        return bereit;
    }

    public boolean getLaufend() {
        return laufend;
    }

    @Override
    public String toString() {
        return "Prozess:\nName: "+name + "\nPriorität: " + prio + "\nBereit: " + (bereit ? "Ja" : "Nein") + "\nLaufend: " + (laufend ? "Ja" : "Nein")+"\n";
    }
}
