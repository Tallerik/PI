package de.hems.tallerik.pi.sort.compareable;

public class Prozess implements Comparable<Prozess>{

    private String name;
    private boolean laufend;
    private boolean bereit;
    private int prio;

    public Prozess(String name, boolean laufend, boolean bereit, int prio) {
        this.name = name;
        this.laufend = laufend;
        this.bereit = bereit;
        this.prio = prio;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLaufend() {
        return laufend;
    }

    public void setLaufend(boolean laufend) {
        this.laufend = laufend;
    }

    public boolean isBereit() {
        return bereit;
    }

    public void setBereit(boolean bereit) {
        this.bereit = bereit;
    }

    public int getPrio() {
        return prio;
    }

    public void setPrio(int prio) {
        this.prio = prio;
    }

    @Override
    public int compareTo(Prozess p) {
        return Integer.compare(this.getPrio(), p.getPrio());
    }

    @Override
    public String toString() {
        return "Prozess: " +
                "name = " + name + ", "+ fixSpace(name, 25) +

                " laufend = " + pretty(laufend) +
                ", bereit = " + pretty(bereit) +
                ", prio = " + prio;
    }

    private String fixSpace(String text, int count) {
        count = count - text.length();
        String o = "";
        for (int i = 0; i < count; i++) {
            o += " ";
        }
        return o;
    }
    private String pretty(boolean b) {
        if(b) {
            return "Y";
        } else {
            return "N";
        }
    }
}
