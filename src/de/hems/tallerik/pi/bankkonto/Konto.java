package de.hems;

import java.util.Random;

import org.json.JSONObject;
/**
 * Extends Logable 
 */
public class Konto extends Logable {
    int id;
    String owner;
    double money = 0;
    double zinssatz;
    double limit;
    boolean locked = false;
    int nummer;

    Bank bank; // Bank the Konto reffers to
    String password;

    /**
     * Used to initialize a new Konto
     * @param owner Name of owner
     * @param limit Payout limit
     * @param b Bank
     * @param password Login password
     */
    public Konto(String owner, String limit, Bank b, String password) {
        id = App.kontoindex + 1;
        App.kontoindex++;
        Random r = new Random();
        nummer = 100000 + r.nextInt(900000);
        zinssatz = b.getZinsen();
        this.owner = owner;
        this.limit = Double.parseDouble(limit);
        this.bank = b;
        this.password = password;
    }

    public Konto(JSONObject obj) {

        id = obj.getInt("id");
        nummer = obj.getInt("nummer");
        owner = obj.getString("owner");
        money = obj.getDouble("money");
        zinssatz = obj.getDouble("zinssatz");
        limit = obj.getDouble("limit");
        locked = obj.getBoolean("locked");
        password = obj.getString("password");
        App a = App.getApp();
        bank = App.getApp().getBank(obj.getString("bank"));
    }

    public JSONObject getAsJSON() {
        JSONObject out = new JSONObject();
        out.put("id", id);
        out.put("nummer", nummer);
        out.put("owner", owner);
        out.put("money", money);
        out.put("zinssatz", zinssatz);
        out.put("limit", limit);
        out.put("locked", locked);
        out.put("password", password);
        out.put("bank", bank.getName());
        return out;
    }


    /**
     * Tests if Username/password is correct
     * @param name Username
     * @param pw Password
     * @return boolean: true if login successfully otherwise false
     */
    public boolean login(String name, String pw) {
        return name.equals(owner) && this.password.equals(pw);
    }

    /**
     * Payin
     * @param count How much money you want to pay in
     */
    public void einzahlen(double count) {
        money = money + count;
    }

    /**
     * Payout money
     * @param count:double How much money you want to payout
     * @return boolean
     */
    public boolean auszahlen(double count) {
        if((money - count) > 0 && count <= limit) {
            money = money - count;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Tests if the money is positiv on this Konto
     * @return boolean
     */
    public boolean istsoll() {
        return money > 0;
    }

    /**
     * Give Zinsen to Konto
     */
    public void zinsenzahlen() {
        double zins = money * (zinssatz / 100);
        money = money + zins;
    }

    /**
     * Lock the Konto. User is unable to do any transaction after this
     */
    public void lock() {
        locked = true;
    }
 
    // Setter
    public void setLimit(double limit) {
        this.limit = limit;
    }

    /**
     * Getter
     */
    public Bank getBank() {
        return bank;
    }
    public int getNummer() {
        return nummer;
    }
    public String getOwner() {
        return owner;
    }
    public double getMoney() {
        return money;
    }
}
