package de.hems.tallerik.pi.klausur;

import java.util.Arrays;

public class Regal {
    static int addindex = 0;
    Fach[] faecher;

    public Regal() {
        faecher = new Fach[12];
    }

    public void hinzufuegen(Fach f) {
        faecher[addindex] = f;
        addindex++;
    }

    public Fach[] getFaecher() {
        return faecher;
    }

    public void setFaecher(Fach[] faecher) {
        this.faecher = faecher;
    }



    public Buch[] sucheFachMitMeistenBuechern() {
        int count = 0;
        int index = -1;
        int bez = 0;

        for(int i = 0; i < faecher.length; i++) {
            if(faecher[i] == null) {continue;}
            if(faecher[i].getBuecher().size() >= count && Integer.parseInt(faecher[i].getBezeichnung()) > bez) {
                count = faecher[i].getBuecher().size();
                index = i;
                bez = Integer.parseInt(faecher[i].getBezeichnung());
                System.out.println();
            }
        }
        if(index == -1) {
            return null;
        }
        return faecher[index].getBuecher().toArray(new Buch[0]);
    }

    public Buch[] sucheFachMitMeistenBuechern2() {
        int buecher = 0;
        Fach fach = null;
        for (int i = 0; i < faecher.length; i++) {
            if(faecher[i] == null) {
                continue;
            }
            int aktuelleAnzahl = faecher[i].getBuecher().size();
            if(aktuelleAnzahl > buecher) {
                buecher = aktuelleAnzahl;
                fach = faecher[i];
            }
            if(aktuelleAnzahl == buecher) {
                if(Integer.parseInt(fach.bezeichnung) < Integer.parseInt(faecher[i].getBezeichnung())) {
                    fach = faecher[i];
                }
            }
        }
        if(buecher == 0) {
            return null;
        }
        return fach.buecher.toArray(new Buch[1]);

    }

    public Buch[] sucheFachMitMeistenBuechern3() {
        Fach found = null;
        for (int i = 0; i < faecher.length; i++) {
            Fach f = faecher[i];
            if (found == null) {
                found = f;
                continue;
            }
            if(f == null) {
                continue;
            }
            if(found.getBuecher().size() == f.getBuecher().size()) {
                if(Integer.parseInt(found.getBezeichnung()) < Integer.parseInt(f.getBezeichnung())) {
                    found = f;
                }
            } else if(found.getBuecher().size() < f.getBuecher().size()) {
                found = f;
            }
        }
        if(found == null) {
            return null;
        }
        Buch[] ret = new Buch[found.getBuecher().size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = found.buecher.get(i);
        }
        return ret;
    }
}
