package de.hems.tallerik.pi.vererbung;

public abstract class Person {
    protected String name, gebdatum;
    protected int monatsgehalt, personalnummer;
    protected static int zaehler = 1000;

    public Person(String name, String gebdatum, int mgehalt) {
        this.name = name;
        this.gebdatum = gebdatum;
        this.monatsgehalt = mgehalt;
        personalnummer = zaehler;
        zaehler++;
    }
    public String gruessen() {
        return "Guten Tag";
    }
    public int berechneJahresgehalt() {
        return monatsgehalt * 12;
    }

    public String toString() {
        return "Person: " + name + " Gehalt: " + monatsgehalt;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGebdatum() {
        return gebdatum;
    }

    public void setGebdatum(String gebdatum) {
        this.gebdatum = gebdatum;
    }

    public int getMonatsgehalt() {
        return monatsgehalt;
    }

    public void setMonatsgehalt(int monatsgehalt) {
        this.monatsgehalt = monatsgehalt;
    }

    public int getPersonalnummer() {
        return personalnummer;
    }

    public void setPersonalnummer(int personalnummer) {
        this.personalnummer = personalnummer;
    }
}
