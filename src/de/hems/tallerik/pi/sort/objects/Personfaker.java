package de.hems.tallerik.pi.sort.objects;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Personfaker {

    private List<String> first;
    private List<String> last;

    private Random r;

    private boolean searchName;

    public Personfaker(boolean searchName) {
        this.searchName = searchName;
        r = new Random();
        try {
            first = readURL("https://raw.githubusercontent.com/dominictarr/random-name/master/first-names.txt");
            last = readURL("https://raw.githubusercontent.com/dominictarr/random-name/master/names.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Person fakePerson() {
        String name = first.get(r.nextInt(first.size())) + " " + last.get(r.nextInt(last.size()));
        int num = r.nextInt(99999999);
        return new Person(name, num, this.searchName);
    }

    public Person[] fakePerson(int c) {
        Person[] feld = new Person[c];
        for (int i = 0; i < c; i++) {
            feld[i] = fakePerson();
        }
        return feld;
    }


    private List<String> readURL(String adress) throws IOException {
        URL url = new URL(adress);
        Scanner s = new Scanner(url.openStream());
        List<String> list = new ArrayList<>();
        while (s.hasNext()) {
            list.add(s.next());
        }
        return list;
    }
}
