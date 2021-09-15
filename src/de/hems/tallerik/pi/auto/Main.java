package de.hems.tallerik.pi.auto;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Auto bmw = new Auto("BMW", "E36", 2003);




        Auto a1 = new Auto("Opel", "Cascada", 2016, 3, 167, 180,
                normaleReifen(), 5, genFeaturelist("ABS", "ASR", "Navigationssystem", "Tempomat"), 80,
                50, "Benzin", 7, 2.4, 0.0);
        System.out.println(a1);
        a1.fahren(200);
    a1.getRaeder().forEach(element -> {
        System.out.print(element.getDruck());
        System.out.println(" Bar");
    });

    System.out.print(a1.getTankinhalt());
    System.out.println("L Tankinhalt");

    System.out.println(a1.volltanken());


    }

    private static List<Reifen> normaleReifen() {
        List<Reifen> reifenList = new ArrayList<Reifen>();

        reifenList.add(new Reifen("Toyo", 4.0));
        reifenList.add(new Reifen("Toyo", 4.0));
        reifenList.add(new Reifen("Toyo", 4.0));
        reifenList.add(new Reifen("Toyo", 4.0));
        return reifenList;
    }
    public static List<String> genFeaturelist(String... feature) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < feature.length; i++) {
            list.add(feature[i]);
        }
        return list;
    }
}
