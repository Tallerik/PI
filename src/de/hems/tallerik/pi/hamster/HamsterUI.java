package de.hems.tallerik.pi.hamster;

public class HamsterUI {
    public static void main(String[] args) {
        Hamster h = new Hamster("Tim", 0, "Braun", 100);
        System.out.println(h.gruessen());
        System.out.println(h.schlafen(5));
        System.out.println(h.rennen(5));
        System.out.println(h.fressen(10));
        System.out.println(h);

    }
}
