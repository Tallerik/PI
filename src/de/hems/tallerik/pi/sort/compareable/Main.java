package de.hems.tallerik.pi.sort.compareable;

import de.hems.tallerik.pi.sort.compareable.comperator.ProzessBereitComperator;
import de.hems.tallerik.pi.sort.compareable.comperator.ProzessLaufendComperator;
import de.hems.tallerik.pi.sort.compareable.comperator.ProzessNameComperator;

import java.util.Collections;
import java.util.List;

public class Main {

    private static List<Prozess> list;

    public static void main(String[] args) {

        Prozessfaker faker = new Prozessfaker();
        list = faker.fakeProzess(20);

        System.out.println("Unsortiert");
        printList(list);
        System.out.println();

        System.out.println("Sortiert nach Priorität");
        Collections.sort(list);
        printList(list);
        System.out.println();

        System.out.println("Sortiert nach Name");
        list.sort(new ProzessNameComperator());
        printList(list);
        System.out.println();

        System.out.println("Sortiert nach Laufend");
        list.sort(new ProzessLaufendComperator());
        printList(list);
        System.out.println();

        System.out.println("Sortiert nach Bereit");
        list.sort(new ProzessBereitComperator());
        printList(list);

        System.out.println("Fertig!");

    }


    private static void printList(List l) {
        for (Object o :l) {
            System.out.println(o.toString());
        }
    }
}
