package de.hems.tallerik.pi.klausur;

import java.util.Arrays;

public class KlausurUI {
    public static void main(String[] args) {
        Regal r = new Regal();

        Fach f1 = new Fach("ET", 20, "01");
        Fach f2 = new Fach("PI", 20, "02");
        Fach f3 = new Fach("PH", 20, "10");
        Fach f4 = new Fach("MA", 20, "11");
        Fach f5 = new Fach("SP", 20, "20");

        f1.getBuecher().add(new Buch("Top Buch", "ET", 2.0, 1, 1));
        f1.getBuecher().add(new Buch("Top Buch2", "ET", 2.0, 1, 1));

        f2.getBuecher().add(new Buch("Top Buch3", "PI", 2.0, 1, 1));
        f2.getBuecher().add(new Buch("Top Buch4", "PI", 2.0, 1, 1));
        f2.getBuecher().add(new Buch("Top Buch5", "PI", 2.0, 1, 1));

        f3.getBuecher().add(new Buch("Top Buch6", "PH", 2.0, 1, 1));

        f4.getBuecher().add(new Buch("Top Buch7", "MA", 2.0, 1, 1));
        f4.getBuecher().add(new Buch("Top Buch8", "MA", 2.0, 1, 1));

        f5.getBuecher().add(new Buch("Top Buch9", "MA", 2.0, 1, 1));
        f5.getBuecher().add(new Buch("Top Buch10", "MA", 2.0, 1, 1));
        f5.getBuecher().add(new Buch("Top Buch11", "MA", 2.0, 1, 1));


        r.hinzufuegen(f1);
        r.hinzufuegen(f2);
        r.hinzufuegen(f3);
        r.hinzufuegen(f4);
        r.hinzufuegen(f5);

        System.out.println(Arrays.toString(r.sucheFachMitMeistenBuechern()));


    }


}
