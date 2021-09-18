package de.hems;

import java.util.Random;

import org.json.JSONObject;
/**
 * Extends Logable 
 */
public class Bank extends Logable {
    String name;
    int banklZ;
    int id;
    double zinsen = 2.95;
    String nutzer;
    String pw;

    /**
     * Used to initialize a new Bank
     * 
     * @param name Name of the Bank
     * @param nutzer Loginname of the Banker
     * @param pw Loginpw of the Banker
     */
    public Bank(String name, String nutzer, String pw) {
        Random r = new Random();
        banklZ = 100000 + r.nextInt(900000);
        this.name = name;
        id = App.bankindex + 1;
        App.bankindex++;
        this.nutzer = nutzer;
        this.pw = pw;
    }

    public Bank(JSONObject obj) {
        this.name = obj.getString("name");
        this.nutzer = obj.getString("nutzer");
        this.pw = obj.getString("pw");

        this.banklZ = obj.getInt("banklZ");
        this.id = obj.getInt("id");

        this.zinsen = obj.getDouble("zinsen");

        if(App.bankindex < id) {
            App.bankindex = id;
        }
    }
    public JSONObject getAsJSON() {
        JSONObject out = new JSONObject();
        out.put("name", name);
        out.put("nutzer", nutzer);
        out.put("pw", pw);
        out.put("banklZ", banklZ);
        out.put("id", id);
        out.put("zinsen", zinsen);
        return out;
    }

    // Getter
    public int getId() {
        return id;
    }
    public int getBanklZ() {
        return banklZ;
    }
    public String getName() {
        return name;
    }
    public double getZinsen() {
        return zinsen;
    }
    
    /**
     * Tests if Username/password is correct
     * @param name Username
     * @param pw Password
     * @return boolean: true if login successfully otherwise false
     */
    public boolean login(String name, String pw) {
        return name.equals(nutzer) && this.pw.equals(pw);
    }

    /**
     * Change zinsen
     * @param zinsen new Value
     */
    public void setZinsen(double zinsen) {
        this.zinsen = zinsen;
    }

    /**
     * Give Zinsen to every unlocked account reffering to this bank
     */
    public void zinsen() {
        int i = 0;
        for (Konto k : App.getApp().getKontos()) {
            if(k.bank == this && !k.locked) {
                i++;
                k.zinsenzahlen();
            }
        }
        System.out.println("Es wurden " + i + " konten verzinst.");
    }

    /**
     * Basic state print
     */
    public void status() {
        System.out.println("Name: " + name);
        System.out.println("Bankleitzahl: " + banklZ);
        System.out.println("Zinsen: " + zinsen + "%");
        System.out.println("Nutzername: " + nutzer);
    }
}

