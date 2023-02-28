package de.hems.tallerik.pi.sort.objects;

import java.util.Arrays;
import java.util.Random;

public class Main {
    private static String[] fakeNames = {"Antje Kaiser","Jan Fenstermacher","Matthias Gärtner","Mary R. Gallman", "Robert M. Butler"};
    private static Random r;

    private static final boolean compareName = true;

    public static void main(String[] args) {
        r = new Random();

        Person[] feld = fakePersons(15);
        System.out.println(Arrays.toString(feld));

        ObjectQuickSort<Person> sort = new ObjectQuickSort<>();
        sort.quicksort(feld);

        System.out.println(Arrays.toString(feld));
    }

    private static Person[] fakePersons(int count) {
        Person[] feld = new Person[count];
        for (int i = 0; i < count; i++) {
            feld[i] = fakePerson();
        }
        return feld;
    }

    private static Person fakePerson() {
        String name = fakeNames[r.nextInt(fakeNames.length)];
        return new Person(name, r.nextInt(1000000000), compareName);
    }

}
