package de.hems.tallerik.pi.bucher;

public class BucherUI {

    public static void main(String[] args) {

        Regal regal =  new Regal("Jacky", 250);


        // Bücher hinzufügen
        regal.getRegalFachByName("ET2").addBook(new Buch("Fachkunde Praxis", 35.5, 45, 10, 35, "12-345"));
        regal.getRegalFachByName("ET2").addBook(new Buch("Elektronik 1", 50.8, 40, 10, 25, "70-651"));

        regal.getRegalFachByName("PHY").addBook(new Buch("Mechanik für Profis", 98, 35, 10, 30, "93-112"));

        regal.getRegalFachByName("INF2").addBook(new Buch("Algorithmen Praxis", 120, 65, 12, 35, "11-346"));

        regal.getRegalFachByName("ET3").addBook(new Buch("Formelsammlung Max", 75.5, 50, 8, 40, "03-388"));


        printRegal(regal);

        regal.getRegalFachByName("PHY").getBucher().get(0).setAusgeliehen(true);
        System.out.println("Buch ausgeliehen\n");
        printRegal(regal);
    }


    private static void printRegal(Regal regal) {
        System.out.println(regal);
        regal.getAllBooksSortByCat().forEach((s, buches) -> {
            String bu = "";
            for (Buch b: buches) {
                bu = bu + b.getTitel() + (b.isAusgeliehen() ? " (Ausgeliehen)" : "") +", ";
            }
            System.out.println(s + "  -  " + bu);
        });
    }
}
