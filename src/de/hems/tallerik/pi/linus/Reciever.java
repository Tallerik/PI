package de.hems.tallerik.pi.linus;

public class Reciever {

    int inX = 0;
    int inY = 0;
    int inF = 0;

    boolean up;
    boolean down;
    boolean left;
    boolean right;
    boolean fire;

    public void run() {
        fire = inF == 1;

        up = (inX == 1);
        down = (inX == -1);
        left = (inY == -1);
        right = (inY == 1);
    }
}
