package de.hems.tallerik.pi.hamster;

public class Hamster {
    private String name;
    private int hunger;
    private String farbe;
    private int energie;


    public Hamster(String name, int hunger, String farbe, int energie) {
        this.name = name;
        this.hunger = hunger;
        this.farbe = farbe;
        this.energie = energie;
    }

    public String schlafen(int min) {

        hunger = hunger + min;
        energie = energie + min;
        return "Gute Nacht (" + min + " Minuten)";
    }
    public String fressen(int korner) {
        if(hunger == 0) {
            return "Kein Hunger";
        }
        hunger = hunger - korner;
        if(hunger < 0)
            hunger = 0;
        energie = energie + ((int)Math.floor(korner / 2.0));
        return "Lecker!!";
    }
    public String rennen(int m) {

        for (int i = 0; i < m; i++) {
            if(energie > 0) {
                energie = energie - 2;
                hunger++;
                if(hunger >= 200) {
                    return "Verhungert";
                }
            } else
                return "Keine Energie mehr";
        }
        return m + " Meter gelaufen";
    }
    public String gruessen() {
        return "Hallo, da oben!";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public String getFarbe() {
        return farbe;
    }

    public void setFarbe(String farbe) {
        this.farbe = farbe;
    }

    public int getEnergie() {
        return energie;
    }

    public void setEnergie(int energie) {
        this.energie = energie;
    }

    @Override
    public String toString() {
        return "Hamster Name: " + name + "\n Hunger: " + hunger + " Energie: " + energie + " Farbe: " + farbe;
    }
}
