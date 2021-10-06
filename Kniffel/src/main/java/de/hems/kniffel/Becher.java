package de.hems.kniffel;


import java.util.concurrent.ThreadLocalRandom;

/**
 * Klasse Becher. Würfelfunktionen
 */
public class Becher {

    /**
     * Die aktuelle gewürfelte Zahlen
     */
    private int[] wurfel;


    /**
     * Initialisiert Becher und weist Standardwerte zu.
     */
    public Becher() {
        wurfel = new int[]{0, 0, 0, 0, 0};
    }

    /**
     * Würfelt alle Würfel neu
     *
     * @return Gewürfelte Werte als int[]
     */
    public int[] wuerfeln() {
        for (int i = 0; i < wurfel.length; i++) {
            wurfel[i] = random();
        }
        return wurfel;
    }

    /**
     * Würfelt angegebene Würfel neu
     *
     * @param slots Würfel, welche neu gewürfelt werden sollen (INDEXES!!!)
     * @return Gewürfelte Werte als int[]
     */
    public int[] wuerfeln(int[] slots) {

        try {
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
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Indexfehler");
        }

        return wurfel;

    }

    /**
     * Getter
     *
     * @return Aktuell gewürfelte Augenzahlen
     */
    public int[] getWurfel() {
        return wurfel;
    }

    /**
     * Generiert eine Random zahl zwischen 1 und 6 (Inklusive)
     * @return Random Zahl
     */
    private int random() {
        return ThreadLocalRandom.current().nextInt(1, 6 + 1); // 0 is the start value (which is inclusive) and 6 is the end (not inclusive) but + 1 make it inclusive;
    }

}
