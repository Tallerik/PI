package de.hems.tallerik.pi.bucher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Regal {

    private String name;
    private double preis;
    private RegalFach[][] regalfacher;

    public Regal(String name, double preis) {
        this.name = name;
        this.preis = preis;

        regalfacher = new RegalFach[3][3];
        constructDefaultRegal();
    }

    private void constructDefaultRegal() {
        regalfacher[0][0] = new RegalFach("ET1",50, 60, 40);
        regalfacher[0][1] = new RegalFach("ET2",50, 60, 40);
        regalfacher[0][2] = null;

        regalfacher[1][0] = new RegalFach("INF1",70, 40, 40);
        regalfacher[1][1] = new RegalFach("PHY",70, 40, 40);
        regalfacher[1][2] = new RegalFach("INF2",70, 40, 40);

        regalfacher[2][0] = new RegalFach("MA",40, 60, 40);
        regalfacher[2][1] = new RegalFach("ET3",80, 60, 40);
        regalfacher[2][2] = null;

    }


    public String getName() {
        return name;
    }

    public double getPreis() {
        return preis;
    }

    public void setName(String nameNeu) {
        name = nameNeu;
    }
    public void setPreis(double preisNeu) {
        preis = preisNeu;
    }

    public double berechneGesamtwert() {
        double price = 0;
        for (int i = 0; i < regalfacher.length; i++) {
            for (int j = 0; j < regalfacher[i].length; j++) {
                if(regalfacher[i][j] == null)
                    continue;
                for (Buch b : regalfacher[i][j].getBucher()) {
                    price += b.getPreis();
                }

            }
        }
        return price;
    }

    public RegalFach getRegalFachByName(String name) {
        for (int i = 0; i < regalfacher.length; i++) {
            for (int j = 0; j < regalfacher[i].length; j++) {
                if(regalfacher[i][j] == null)
                    continue;
                if(regalfacher[i][j].getName().equals(name)) {
                    return regalfacher[i][j];
                }

            }
        }
        return null;
    }

    public List<Buch> getallbooks() {
        List<Buch> bucher = new ArrayList<>();
        for (int i = 0; i < regalfacher.length; i++) {
            for (int j = 0; j < regalfacher[i].length; j++) {
                if(regalfacher[i][j] == null)
                    continue;
                bucher.addAll(regalfacher[i][j].getBucher());
            }
        }
        return bucher;
    }

    public HashMap<String, List<Buch>> getAllBooksSortByCat() {
        HashMap<String, List<Buch>> map = new HashMap<>();
        for (int i = 0; i < regalfacher.length; i++) {
            for (int j = 0; j < regalfacher[i].length; j++) {
                if(regalfacher[i][j] == null)
                    continue;
                map.put(regalfacher[i][j].getName(), regalfacher[i][j].getBucher());
            }
        }
        return map;
    }

    public RegalFach[][] getRegalfacher() {
        return regalfacher;
    }

    public void setRegalfacher(RegalFach[][] regalfacherNeu) {
        regalfacher = regalfacherNeu;
    }

    @Override
    public String toString() {
        String s = "Bücherregal: " + name + "\nGesamtwert: " + berechneGesamtwert();
        return s;
    }
}
