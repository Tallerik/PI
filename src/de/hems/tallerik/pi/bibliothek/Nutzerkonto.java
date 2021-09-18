package de.hems.tallerik.pi.bibliothek;

import org.json.JSONObject;

/*
    Datenobjekt Nutzerkonto
    Beinhaltet Hilfsfunktionen und Getter/Setter
*/
public class Nutzerkonto {
    private int nutzerID;
    private String vorname;
    private String nachname;
    private int bucherEntliehen;
    private int ueberzogeneBucher;
    private double strafgebuehren;
    private boolean geloscht = false;

    /*
        Konstruktor um mit den Daten aus einem JSON-Objekt die Klasse zu initialisieren.
        @param obj:JSONObject JSONObjekt mit den Daten eines vorher gespeicherten Nutzerkontos.
    */
    Nutzerkonto(JSONObject obj){
        this.nutzerID = obj.getInt("id");
        this.bucherEntliehen = obj.getInt("entliehen");
        this.ueberzogeneBucher = obj.getInt("uberzogen");

        this.strafgebuehren = obj.getDouble("strafe");

        this.geloscht = obj.getBoolean("deleted");

        this.vorname = obj.getString("vorname");
        this.nachname = obj.getString("nachname");
    }

    /*
        Konstruktor um ein Nutzerkonto mit Standardwerten und angegebenen Vor-/Nachnamen
        @param vornameParam:String Vorname des Nutzerkonto-Besitzer
        @param nachnameParam:String Nachname des Nutzerkonto-Besitzer
    */
    Nutzerkonto(String vornameParam, String nachnameParam){
        nutzerID = App.ID_INDEX++;
        vorname = vornameParam;
        nachname = nachnameParam;
        bucherEntliehen = 0;
        ueberzogeneBucher = 0;
        strafgebuehren = 0.0;
    }

    /*
        Aktualisiert den Wert strafgeueren, bestehend aus ueberzogeneBucher
        @return void
    */
    void strafgebuehrenBerechnen() {
        strafgebuehren = ueberzogeneBucher * 2.5;
    }

    /*
        Methode um ein Nutzerkonto zu deaktivieren.
        Setzt alle Werte zu 0 und geloscht zu true
        @return void
    */
    void loeschenNutzerkonto(){
        nutzerID = 0;
        String tname = vorname + " " + nachname;
        vorname = "GELÖSCHT";
        nachname = vorname;
        bucherEntliehen = 0;
        ueberzogeneBucher = 0;
        strafgebuehren = 0;
        geloscht = true;
        System.out.println("Nutzerkonto von " + tname + " wurde erfolgreich gelöscht.");
    }

    /*
        Methode um ein Buch auszuleihen.
        Testet ob Strafgebüren zu hoch sind, um ein weiteres Buch auszuleihen.
        @return boolean true wenn funktion erfolgreich ausgeführt werden konnte. Ansonsten false
    */
    boolean buchAusleihen(){
        if(strafgebuehren > 0) {
            return false;
        }
        bucherEntliehen += 1;
        return true;
    }

    /*
        Methode um ein Buch zurückzugeben.
        Prüft ob nicht schon alle Bücher zurückgegeben sind
        @return void
    */
    void buchZurueckgeben(){
        if (bucherEntliehen > 0){
            bucherEntliehen--;
        }
    }

    /*
        Methode um Strafgebüren zu zahlen.
        Testet ob die Geldmenge passt.
        @param geld:double Geldmenge
        @return boolean Gibt true bei Erfolg und false falls Geld nicht passt zurück
    */
    boolean strafgebuehrenBezahlen(double geld){
        if (strafgebuehren - geld != 0){ return false; }

        ueberzogeneBucher = 0;
        strafgebuehren = 0.0;

        return true;
    }

    /*
        Gibt das Nutzerkonto als JSON-Objekt zurück.
        @return JSONObjekt mit allen Daten des Nutzerkontos
    */
    JSONObject getAsJSON() {
        JSONObject obj = new JSONObject();
        obj.put("id", getNutzerID());
        obj.put("vorname", getVorname());
        obj.put("nachname", getNachname());
        obj.put("entliehen", getBucherEntliehen());
        obj.put("uberzogen", getUeberzogeneBucher());
        obj.put("strafe", getStrafgebuehren());
        obj.put("deleted", getGeloscht());
        return obj;
    }

    /*
        Getter
    */
    int getNutzerID(){
        return nutzerID;
    }
    String getVorname(){
        return vorname;
    }
    String getNachname(){
        return nachname;
    }
    int getBucherEntliehen(){
        return bucherEntliehen;
    }
    int getUeberzogeneBucher(){
        return ueberzogeneBucher;
    }
    double getStrafgebuehren(){
        strafgebuehrenBerechnen();
        return strafgebuehren;
    }
    boolean getGeloscht(){
        return geloscht;
    }
}