package de.hems.tallerik.pi.linus;

public class Sender {

    boolean up;
    boolean down;
    boolean left;
    boolean right;
    boolean fire;


    int outx = 0;
    int outy = 0;
    int outf = 0;


    private void run() {
        outf = (fire) ? 1 : 0;

        if(up) {
            outy = 1;
        }
        if(down) {
            outy = -1;
        }
        if(left) {
            outx = -1;
        }
        if(right) {
            outx = 1;
        }
    }

}
