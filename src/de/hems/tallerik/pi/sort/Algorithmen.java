package de.hems.tallerik.pi.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Algorithmen {


    private static final boolean pretty = true;

    private static List<int[]> cache = new ArrayList<>();
    private static int[] def;

    public static void main(String[] args) {

        int[] feld = new int[] {3,1,5,3,6,8,6,3,7,5,89,2,4};
        //int[] feld = random(20);
        def = feld.clone();
        System.out.println();
        System.out.println("BubbleSort");
        prt(bubblesort(feld.clone()));
        System.out.println();
        System.out.println("InsertionSort:");
        prt(insertionsort(feld.clone()));
        System.out.println();
        System.out.println("SelectionSort:");
        prt(selectionsort(feld).clone());
    }


    /**
     * BubbleSort Algorithmus nach Pseudecode aus der Aufgabenstellung
     * @param array Array aus int zum Sortieren
     * @return Sortiertes array
     */
    public static int[] bubblesort(int[] array) {
        for (int i = array.length; i >= 0; i--) {
            for (int j = 0; j < i-1; j++) {
                if(array[j] > array[j+1]) {
                    swap(array, j, j + 1);
                }
            }
            prtd(array);
        }
        return array;
    }


    /**
     * InsertionSort Algorithmus nach Pseudecode aus der Aufgabenstellung
     * @param array Array aus int zum Sortieren
     * @return Sortiertes array
     */
    public static int[] insertionsort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int wert = array[i];
            int j = i;
            while (j > 0 && array[j-1] > wert) {
                array[j] = array[j-1];
                j -= 1;

            }
            array[j] = wert;
            prtd(array);
        }
        return array;
    }

    /**
     * SelectionSort Algorithmus nach Pseudecode aus der Aufgabenstellung
     * @param array Array aus int zum Sortieren
     * @return Sortiertes array
     */
    public static int[] selectionsort(int[] array) {
        for (int i = 0; i < array.length-1; i++) {
            int minIndex = i;
            for (int j = i+1; j < array.length; j++) {
                if(array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
            prtd(array);
        }
        return array;
    }


    /**
     * Tauscht zwei felder in einem array
     * @param array Das Datenfeld
     * @param i1 Index 1
     * @param i2 Index 2
     * @return verändertes Feld
     */
    private static int[] swap(int[] array, int i1, int i2) {
        int v1 = array[i1];
        int v2 = array[i2];
        array[i1] = v2;
        array[i2] = v1;
        return array;
    }

    /**
     * Ausgabe des resultats
     * @param f
     */
    private static void prt(int[] f) {
        if(pretty) {
            pt(def);
            int countUnchanged = 0;
            int[] temp = def;
            for (int i = 0; i < cache.size(); i++) {
                int[] curr = cache.get(i);
                boolean changed = false;
                String s = "[";
                for (int j = 0; j < curr.length; j++) {
                    if(temp[j] != curr[j]) {
                        s += "[" + curr[j] + "], ";
                        changed = true;
                    } else {
                        s += curr[j] + ", ";
                    }
                }
                if(changed) {
                    s = s.substring(0, s.length()-2) + "]";
                    System.out.println(s);
                } else {
                    countUnchanged++;
                }
                temp = cache.get(i);
            }
            System.out.println(Arrays.toString(f));
            System.out.println(countUnchanged + " Überläufe ohne Änderungen");
        } else {

            System.out.println(Arrays.toString(f));
        }
        cache = new ArrayList<>();
    }

    /**
     * Speichert zwischenschritt (pretty-mode) oder gibt ihn aus (normal-mode)
     * @param f Feld zum Zeitpunkt des Zwischenschrittes.
     */
    private static void prtd(int[] f) {
        if(pretty) {
            cache.add(f.clone());
        } else {
            System.out.println(Arrays.toString(f));
        }
    }

    /**
     * Gibt ein Feld aus, nutzt Arrays.toString()
     * @param f Feld zum ausgeben
     */
    private static void pt(int[] f) {
        System.out.println(Arrays.toString(f));
    }

    /**
     * Generiert ein Feld von der Länge Count und füllt es mit Zufallszahlen
     * @param count Länge des Feldes
     * @return Generiertes Feld
     */
    private static int[] random(int count) {
        Random r = new Random();
        int[] array = new int[count];
        for (int i = 0; i < count; i++) {
            array[i] = r.nextInt(100);
        }
        return array;
    }

}
