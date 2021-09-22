package de.hems.tallerik.pi.bucher;

public class Buch {

    private String titel;
    private double preis;
    private double height;
    private double width;
    private double depth;
    private String isbn;
    private boolean ausgeliehen;


    public Buch(String titel, double preis, double height, double width, double depth, String isbn) {
        this.titel = titel;
        this.preis = preis;
        this.height = height;
        this.width = width;
        this.depth = depth;
        this.isbn = isbn;
        ausgeliehen = false;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isAusgeliehen() {
        return ausgeliehen;
    }

    public void setAusgeliehen(boolean ausgeliehen) {
        this.ausgeliehen = ausgeliehen;
    }

    @Override
    public String toString() {
        return "Buch: " + titel + ((ausgeliehen) ? " (Ausgeliehen)" : "");
    }
}
