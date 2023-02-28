package de.hems.tallerik.pi.sort;

import java.util.Arrays;

public class QuickSort {


    public static int[] quicksort(int[] feld, int min, int max) {

        if(min < max) {
            int pivot = partition(feld, min, max);

            quicksort(feld, min, pivot - 1);
            quicksort(feld, pivot + 1, max);
        }
        return feld;

    }

    public static int partition(int[] feld, int min, int max) {

        int x = feld[max];
        int i = min - 1;
        for(int j = min; j < max; j++) {
            if (feld[j] <= x) {
                i = i + 1;
                swap(feld, i, j);
            }
        }
        swap(feld, i+1, max);
        return i+1;
    }

    public static void swap(int[] feld, int u, int v) {
        int temp = feld[u];
        feld[u] = feld[v];
        feld[v] = temp;
    }





    public static void main(String[] args) {

        int[] a = {21,23,13,5,11,324,44,6,2,12,22};
        pa(a);
        int[] sort = quicksort(a, 0, a.length-1);

        pa(sort);

    }

    public static void pa(int[] a) {
        System.out.println(Arrays.toString(a));
    }
}
