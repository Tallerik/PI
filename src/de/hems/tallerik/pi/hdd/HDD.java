package de.hems.tallerik.pi.hdd;

public class HDD {

    private String hersteller;
    private double kapazitaet;
    private char partition;

    public HDD(String h, double k) {
        this.hersteller = h;
        this.kapazitaet = k;
        this.partition = 'C';
    }

    //Beispielimplementierung:
    public static void main(String[] args) {
        HDD kingston = new HDD("Kingston", 800.0);
        kingston.setPartition('F');
        System.out.println(kingston);

        HDD samsung = new HDD("Samsung", 1000.0);
        System.out.println(samsung);
    }

    public char getPartition() {
        return partition;
    }

    public double getKapazitaet() {
        return kapazitaet;
    }

    public String getHersteller() {
        return hersteller;
    }

    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    public void setPartition(char partition) {
        this.partition = partition;
    }

    public void setKapazitaet(double kapazitaet) {
        this.kapazitaet = kapazitaet;
    }

    @Override
    public String toString() {
        return "HDD: \nHersteller: " + hersteller + "\nKapazität: " + kapazitaet + "\nPartition: " + partition+"\n";
    }
}
