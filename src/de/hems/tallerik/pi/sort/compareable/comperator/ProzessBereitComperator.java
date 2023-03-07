package de.hems.tallerik.pi.sort.compareable.comperator;

import de.hems.tallerik.pi.sort.compareable.Prozess;

import java.util.Comparator;

public class ProzessBereitComperator  implements Comparator<Prozess> {

    @Override
    public int compare(Prozess o1, Prozess o2) {
        return Boolean.compare(o1.isBereit(), o2.isBereit());
    }
}