package de.hems.tallerik.pi.auto;

import java.util.ArrayList;
import java.util.List;

public class Autohaus {

    private String name;
    private double kapital;

    private List<Auto> autos;
    private List<Reifen> reifenlager;

    public Autohaus(String name) {
        this.name = name;
        autos = new ArrayList<>();
        reifenlager = new ArrayList<>();
        kapital = 100000.0;
    }

    public boolean reifenKaufen(String marke, double max, double price) {
        if(price <= kapital) {
            reifenlager.add(new Reifen(marke, max));
            kapital = kapital - price;
            return true;
        }
        return false;
    }
    public boolean autoKaufen(Auto auto, double price) {
        if(price <= kapital) {
            autos.add(auto);
            kapital = kapital - price;
            return true;
        }
        return false;
    }

    public boolean reifenWechseln(Auto auto, Reifen neu, int slot) {
        if(autos.contains(auto) && reifenlager.contains(neu) && auto.getRaeder().size() > slot) {
            autos.remove(auto);
            reifenlager.remove(neu);
            Reifen alt = auto.getRaeder().get(slot);
            auto.getRaeder().remove(slot);
            auto.getRaeder().add(neu);
            autos.add(auto);
            reifenlager.add(alt);
            return true;
        }
        return false;
    }
}
