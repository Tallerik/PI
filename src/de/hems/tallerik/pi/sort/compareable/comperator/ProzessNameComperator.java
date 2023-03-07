package de.hems.tallerik.pi.sort.compareable.comperator;

import de.hems.tallerik.pi.sort.compareable.Prozess;

import java.util.Comparator;

public class ProzessNameComperator implements Comparator<Prozess> {
    @Override
    public int compare(Prozess o1, Prozess o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
