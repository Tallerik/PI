package de.hems.tallerik.pi.vererbung;

import java.util.ArrayList;
import java.util.List;

public class Firma {
    private List<Person> mitarbeiter;

    public Firma() {
        mitarbeiter = new ArrayList<>();
    }

    public List<Person> getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(List<Person> mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }
}
