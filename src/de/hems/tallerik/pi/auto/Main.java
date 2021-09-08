package de.hems.tallerik.pi.auto;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Auto bmw = new Auto("BMW", "E36", 2003);


        List<String> featureList = new ArrayList<String>();

        featureList.add("ABS");
        featureList.add("ASR");
        featureList.add("Navigationssystem");
        featureList.add("Tempomat");



        Auto a1 = new Auto("Opel", "Cascada", 2016, 3, 167, 180,
                normaleReifen(), 5, genFeaturelist("ABS", "ASR", "Navigationssystem", "Tempomat"), 80,
                50, "Benzin", 7, 2.4);
        System.out.println(a1);
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
