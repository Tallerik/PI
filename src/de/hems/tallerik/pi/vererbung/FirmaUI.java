package de.hems.tallerik.pi.vererbung;

public class FirmaUI {
    public static void main(String[] args) {
        Firma f = new Firma();


        Fuehrungskraft fk1 = new Fuehrungskraft("Tim","010012000", 1000, 5);
        Angestellter a1 = new Angestellter("Tom", 2500, false, 2);
        Fuehrungskraft fk2 = new Fuehrungskraft("Paul","050072001", 1000, 5);
        Angestellter a2 = new Angestellter("Robin", 1800, true, 1);

        f.getMitarbeiter().add(fk1);
        f.getMitarbeiter().add(a1);
        f.getMitarbeiter().add(fk2);
        f.getMitarbeiter().add(a2);

        for (Person p : f.getMitarbeiter()) {
            System.out.println(p);
            System.out.println(p.gruessen());
        }
    }
}
