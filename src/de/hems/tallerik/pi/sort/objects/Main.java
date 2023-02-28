package de.hems.tallerik.pi.sort.objects;

import java.util.Arrays;

public class Main {
    private static String[] fakeNames = {"Antje Kaiser","Jan Fenstermacher","Matthias Gärtner","Mary R. Gallman", "Robert M. Butler"};
    private static final int count = 150;

    private static final boolean compareName = false;

    private static Personfaker faker;

    public static void main(String[] args) {
        faker = new Personfaker(compareName);

        Person[] feld = faker.fakePerson(count);
        System.out.println(Arrays.toString(feld));

        ObjectQuickSort<Person> sort = new ObjectQuickSort<>();
        sort.quicksort(feld);

        System.out.println(Arrays.toString(feld));
    }





}
