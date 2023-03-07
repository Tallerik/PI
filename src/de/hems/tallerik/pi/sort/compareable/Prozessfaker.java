package de.hems.tallerik.pi.sort.compareable;

import de.hems.tallerik.pi.sort.objects.Person;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Prozessfaker {

    private Random r;
    private List<String> names;


    public Prozessfaker() {
        r = new Random();

        try {
            names = readURL("https://pastebin.com/raw/dN17vC4d");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Prozess fakeProzess() {
        return new Prozess(names.get(r.nextInt(names.size())),r.nextBoolean(), r.nextBoolean(), r.nextInt(10));
    }

    public List<Prozess> fakeProzess(int c) {
        List<Prozess> feld = new ArrayList<>();
        for (int i = 0; i < c; i++) {
            feld.add(fakeProzess());
        }
        return feld;
    }


    private List<String> readURL(String address) throws IOException {
        URL url = new URL(address);
        Scanner s = new Scanner(url.openStream());
        List<String> list = new ArrayList<>();
        while (s.hasNext()) {
            list.add(s.next());
        }
        return list;
    }

}
