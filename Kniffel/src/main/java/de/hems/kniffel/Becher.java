package de.hems.kniffel;


import java.util.concurrent.ThreadLocalRandom;

public class Becher {

    int[] wurfel;


    public Becher() {
        wurfel = new int[]{0, 0, 0, 0, 0};
    }

    public int[] wuerfeln() {
        for (int i = 0; i < wurfel.length; i++) {
            wurfel[i] = random();
        }
        return wurfel;
    }

    public int[] wuerfeln(int[] slots) {

        if(slots.length > 3) {
            return wurfel;
        }
        for (int i = 0; i < slots.length; i++) {
            if(slots[i] < 0 ||slots[i] > 5) {
                return wurfel;
            }
        }
        for (int i = 0; i < slots.length; i++) {
            wurfel[slots[i]] = random();
        }
        return wurfel;

    }

    public int[] getWurfel() {
        return wurfel;
    }

    private int random() {
        return ThreadLocalRandom.current().nextInt(0, 6 + 1); // 0 is the start value (which is inclusive) and 6 is the end (not inclusive) but + 1 make it inclusive;
    }

}
