package de.hems.tallerik.pi.sort.objects;

public class ObjectQuickSort <T extends Comparable<T>> {

    public T[] quicksort(T[] feld) {
        return quicksort(feld, 0, feld.length-1);
    }

    private T[] quicksort(T[] feld, int min, int max) {

        if(min < max) {
            int pivot = partition(feld, min, max);

            quicksort(feld, min, pivot - 1);
            quicksort(feld, pivot + 1, max);
        }
        return feld;

    }

    private int partition(T[] feld, int min, int max) {

        T x = feld[max];
        int i = min - 1;
        for(int j = min; j < max; j++) {
            if (feld[j].compareTo(x) <= 0) {
                i = i + 1;
                swap(feld, i, j);
            }
        }
        swap(feld, i+1, max);
        return i+1;
    }

    private void swap(T[] feld, int u, int v) {
        T temp = feld[u];
        feld[u] = feld[v];
        feld[v] = temp;
    }
}
