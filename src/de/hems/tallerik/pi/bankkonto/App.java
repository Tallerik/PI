package de.hems;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;


public final class App {

    // Static informations for styling
    private static final String name = "Krasse Bank";
    private static final String prefix = ConsoleColors.BLUE_BOLD + "[" + ConsoleColors.YELLOW_BOLD  + name + ConsoleColors.BLUE_BOLD + "]" + ConsoleColors.RESET;
    private static final String domain = "krassebank.de"; 

    // Main Instance
    private static App app;
    
    // Current created banks and kontos
    private List<Bank> banks = new ArrayList<>();
    private List<Konto> kontos = new ArrayList<>();
    
    // ID Indexes
    protected static int bankindex = 0;
    protected static int kontoindex = 0;

    private static File config = new File("data.json");

    Scanner sc;
    private App(JSONObject data) {
        app = this;
        JSONArray ba = data.getJSONArray("banks");
        JSONArray ka = data.getJSONArray("kontos");

        for (int i = 0; i < ba.length(); i++) {
            banks.add(new Bank(ba.getJSONObject(i)));
        }
        for (int i = 0; i < ka.length(); i++) {
            kontos.add(new Konto(ka.getJSONObject(i)));
        }

        

        sc = new Scanner(System.in);

        boolean shutdown = false;
        Logable currentlog = null;
        while(!shutdown) {
            String pre = "";
            String suff = "";
            if(currentlog == null) {
                pre = "anonymous";
                suff = domain;
            } else if(currentlog instanceof Bank) {
                pre = "Bank_" + ((Bank)currentlog).nutzer;
                suff = ((Bank)currentlog).name + "." +domain;
            } else if(currentlog instanceof Konto) {
                pre = "Konto_" + ((Konto)currentlog).nummer;
                suff = ((Konto)currentlog).getBank().name  + "." + domain;
            }
                
            List<String> cmds = parse_input(ConsoleColors.GREEN_BOLD_BRIGHT + pre + "@" + suff + ConsoleColors.BLUE +" ~" + ConsoleColors.RESET +"$ ");
            if(currentlog == null) { // Nobody logged in. Falling back to anonymous account
                switch(cmds.get(0)) {
                    case "status":
                        p("Es gibt aktuell " + banks.size() + " Banken und " + kontos.size() + " Konten");
                        break;
                    case "shutdown":
                        shutdown = true;
                        break;
                    case "help":
                        sendhelp();
                        break;
                    case "login":

                        if(cmds.get(1).equalsIgnoreCase("bank")) {

                            if(cmds.size() == 4) {
                                String username = cmds.get(2);
                                String pw = cmds.get(3);
                                Bank bank = null;
                                for (Bank b : banks) {
                                    if(b.login(username, pw)) {
                                        bank = b;
                                    }
                                }
                                if(bank != null) {
                                    currentlog = bank;
                                    p("Eingelogt bei: " + bank.getName());
                                } else {
                                    p("Nutzername oder Passwort falsch");
                                }
                            }
                            
                        } else if(cmds.get(1).equalsIgnoreCase("konto")) {

                            if(cmds.size() == 4) {
                                String username = cmds.get(2);
                                String pw = cmds.get(3);
                                Konto k = null;
                                for (Konto ko : kontos) {
                                    if(ko.login(username, pw)) {
                                        k = ko;
                                    }
                                }
                                if(k != null) {
                                    currentlog = k;
                                    p("Eingelogt als: " + k.getOwner() + " Nr: " + k.getNummer());
                                } else {
                                    p("Nutzername oder Passwort falsch");
                                }
                            }
                        }
                        break;
                    case "create":
                        if(cmds.get(1).equalsIgnoreCase("bank")) {
                                if(cmds.size() == 5) {
                                    Bank b = new Bank(cmds.get(2),cmds.get(3),cmds.get(4));
                                    banks.add(b);
                                    p("Bank wurde erstellt.");
                                    p("Bankleitzahl: " + b.getBanklZ());
                                    p();
                                } else {
                                    sendhelp();
                                }
                        } else if(cmds.get(1).equalsIgnoreCase("konto")) {
                            if(cmds.size() == 6) {
                                Bank b = getBank(cmds.get(2));
                                if(b != null) {
                                    Konto k = new Konto(cmds.get(3), cmds.get(5), b, cmds.get(4));
                                    kontos.add(k);
                                    p("Konto erstellt.");
                                    p("Bankleitzahl: " + b.banklZ);
                                    p("Kontonummer: " + k.nummer);
                                } else {
                                    p("Bank nicht gefunden.");
                                }
                            } else {
                                sendhelp();
                            }
                        }
                        break;
                    default:
                        sendhelp();
                        break;
                }
            } else if(currentlog instanceof Bank) { // Bank Account currently logged in
                switch(cmds.get(0)) {
                    case "logout":
                        currentlog = null;
                        p("Sie wurden erfolgreich ausgeloggt. Bis bald");
                        break;
                    case "status":
                        ((Bank) currentlog).status();
                        break;
                    case "zinsen":
                        ((Bank) currentlog).zinsen();
                        p("Zinsen wurden vergeben");
                        break;
                    case "zinssatz":
                        if(cmds.size() == 1) {
                            p("Der aktuelle Zinssatz beträgt " + ((Bank) currentlog).zinsen + "%.");
                        }
                        if(cmds.size() == 2) {
                            if(isNumeric(cmds.get(1))) {
                                ((Bank) currentlog).setZinsen(Double.parseDouble(cmds.get(1)));
                                p("Der aktuelle Zinssatz beträgt nun " + ((Bank) currentlog).zinsen + "%.");
                            } else {
                                p("Ein Fehler ist aufgetreten");
                            }                            
                        }
                        break;
                    case "create":
                        if(cmds.get(1).equalsIgnoreCase("konto")) {
                            if(cmds.size() == 5) {
                                Konto k = new Konto(cmds.get(2), cmds.get(4), (Bank) currentlog, cmds.get(3));
                                kontos.add(k);
                                p("Konto erstellt.");
                                p("Bankleitzahl: " + ((Bank) currentlog).banklZ);
                                p("Kontonummer: " + k.nummer);
                                
                            } else {
                                sendhelp();
                            }
                        }
                        break;
                    default:
                        sendhelp();
                        break;
                }
            } else if(currentlog instanceof Konto) { // Konto Account currently logged in
                Konto k = (Konto) currentlog;
                switch(cmds.get(0)) {
                    case "logout":
                        currentlog = null;
                        p("Sie wurden erfolgreich ausgeloggt. Bis bald");
                        break;
                    case "status":
                        p("Konto von " + k.getOwner());
                        p("Kontonummer: " + k.getNummer());
                        p("Geld auf dem Konto " + k.getMoney() + "€");
                        p("Auszahlungslimit: " + k.limit + "€");
                        p("Zinssatz: " + k.zinssatz + "%");
                        p("Ihre Bank: " + k.getBank().getName());
                        p("Bankleitzahl: " + k.getBank().getBanklZ());
                        if(k.locked) {
                            p("KONTO IST GESPERRT!");
                        }
                        break;
                    case "einzahlen":
                        if(k.locked) {
                           p("Konto ist gesperrt!");
                           break;
                        }
                        if(cmds.size() == 2) {
                            if(isNumeric(cmds.get(1))) {
                                k.einzahlen(Double.parseDouble(cmds.get(1)));
                                p("Sie haben " + cmds.get(1) + " eingezahlt.");
                            } else {
                                sendhelp();
                            }
                        } else {
                            sendhelp();
                        }
                        break;
                    case "auszahlen":
                    if(cmds.size() == 2) {
                        if(k.locked) {
                            p("Konto ist gesperrt!");
                            break;
                         }
                        if(isNumeric(cmds.get(1))) {
                            if(k.auszahlen(Double.parseDouble(cmds.get(1)))) {
                                p("Sie haben " + cmds.get(1) + " ausgezahlt.");
                            } else {
                                p("Es ist ein Fehler aufgetreten.");
                            }
                            
                        } else {
                            sendhelp();
                        }
                    } else {
                        sendhelp();
                    }
                    break;
                    case "sperren":
                        k.lock();
                        p("Konto wurde gesperrt");
                        break;
                    case "limit":
                        if(cmds.size() == 1) {
                            p("Ihr limit ist auf " + k.limit + "€ eingestellt.");
                        } else if(cmds.size() == 2) {
                            if(isNumeric(cmds.get(1))) {
                                k.setLimit(Double.parseDouble(cmds.get(1)));
                                p("Limit wurde auf " + cmds.get(1) + "€ gesetzt.");
                            } else {
                                sendhelp();
                            }
                        } else {
                            sendhelp();
                        }
                        break;
                    default:
                        sendhelp();
                        break;
                }
            }
        }

        // Shutdown Scanner
        sc.close();
        JSONObject out = new JSONObject();
        // Save all the Data
        JSONArray outk = new JSONArray();
        for (Konto konto : kontos) {
            outk.put(konto.getAsJSON());
        }
        out.put("kontos", outk);

        JSONArray outb = new JSONArray();
        for (Bank bank : banks) {
            outb.put(bank.getAsJSON());
        }
        out.put("banks", outb);

        try {
            FileUtils.write(config, out.toString(), Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        p("Saved all data");
    }

    /**
     * Test if string input is numeric
     * @param str:String Text to test
     * @return boolean if str is numeric or not
     */
    private static boolean isNumeric(String str) { 
        try {  
          Double.parseDouble(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
      }
    /**
     * The beginning of everything.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {

        if(!config.exists()) {
            try {
                config.createNewFile();
                FileUtils.writeStringToFile(config, "{\"banks\":[], \"kontos\":[]}", Charset.forName("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            JSONObject data = new JSONObject(FileUtils.readFileToString(config, Charset.forName("UTF-8")));
            new App(data);
        } catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("Hellou World I fucking hate CAts and fuck Dogs ;)");
    }



    /*
        Funktion um Kommandozeilen-Eingabe auszuwerten.
        Fasst Eingaben in Anführungszeichen zu einem Argument zusammen.

        @param qu:String Eingabe des Nutzers

        @return List<String> Liste mit getrennten eingaben
    */
    private List<String> parse_input(String qu){
        System.out.print(qu + " ");
        // Parse input separated by spaces
        // String parameters (words between '') don't get separated
        List<String> parsed_input = new ArrayList<>();

        String raw_in = sc.nextLine();
        try {
            StringBuilder word = new StringBuilder();
            boolean continuous_word= false;
            // Loop through each letter

            for (char sign : raw_in.toCharArray()){
                // If string detected
                if (sign == '\''){
                    continuous_word = !continuous_word;
                    continue;
                }
                // If space detected and not a string parameter
                if (sign == ' ' && !continuous_word){
                    parsed_input.add(word.toString());
                    word = new StringBuilder();
                    continue;
                }
                // Add letter to word
                word.append(sign);
            }
            parsed_input.add(word.toString());
        }catch (Exception e){
            System.out.println("Oops. Something went wrong!");
        }

        return parsed_input;
    }

    /**
     * Shortcut for println
     * @param s Text to print
     */
    public void p(String s) {
        System.out.println(prefix + " " +s);
    }
    public void p() {
        System.out.println();
    }

    /**
     * Print help
     */
    public void sendhelp() {
        p("----------------------------");
        p(name);
        p("----------------------------");
        p();

        p("Allgemein:");
        p("help");
        p("status");
        p("shutdown");
        p("login bank <name> <pw>");
        p("login konto <name> <pw>");

        p("create bank <name> <nutzer> <pw>");
        p();
        p("Bank:");
        p("status");
        p("zinsen");
        p("zinssatz (Zinssatz)");
        p("logout");
        p("create konto <name> <pw> <limit>");
        p();
        p("Konto:");
        p("status");
        p("einzahlen <menge>");
        p("auszahlen <menge>");
        p("sperren");
        p("limit (menge)");
        p("logout");
        p();
        p();
        p("----------------------------");

    }

    /**
     * Get Bank by name
     * @param name Name of the Bank
     * @return Bank object || null
     */
    public Bank getBank(String name) {
        for (Bank bank : banks) {
            if(bank.name.equalsIgnoreCase(name)) {
                return bank;
            }
        }
        return null;
    }

    /**
     * Get external required Objects
     */
    public List<Konto> getKontos() {
        return kontos;
    }
    public static App getApp() {
        return app;
    }
}

