package de.hems.tallerik.pi.bibliothek;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Main-Klasse für Schulbibliotheks-App
 */
public final class App {

    // Liste aus allen Nutzerkonten
    private static List<Nutzerkonto> nutzerKonten = new ArrayList<>();
    public static int ID_INDEX = 0;
    private static Scanner sc;
    // Konto, welches aktuell angemeldet ist.
    private static Nutzerkonto currentLog = null;
    private static FileHandler handler;
    // Standardkonstruktor
    private App() {
    }

    /*
        Hauptklasse

        @param args:String[] Startargumente des Programms
        @return void
    */
    public static void main(String[] args) {

        handler = new FileHandler("config.json");
        if(!handler.fileExists()) {
            handler.create();
        }

        nutzerKonten = handler.getData();
        System.out.println(nutzerKonten);


        sc = new Scanner(System.in);
        boolean shutdown = false;
        while (!shutdown) {
            pHelp(false);
            List<String> c = parse_input("anonymous@bibliothek:~$ ");
            String cmd = c.get(0);
            c.remove(0);
            List<String> cmdargs = c;
            switch (cmd) {
                case "create":
                    createUser(cmdargs);
                    break;
                case "login":
                    login(cmdargs);
                    break;
                case "shutdown":
                    shutdown = true;
                    break;
                default:
                    p("Dieser Befehl wurde nicht gefunden");
                    break;
            }
        }
        sc.close();
        p("System beendet.");
        handler.saveData(nutzerKonten);
    }

    /*
        Funktion um zu prüfen, ob ein Nutzerkonto mit einem bestimmten Namen existiert.
        @param vorname:String Vorname des Kontos
        @param nachname:String Nachname des Kontos

        @return boolean - Gibt true zurück, wenn ein Konto mit diesem Namen existiert. Ansonsten false
    */
    private static boolean userExists(String vorname, String nachname){
        for(Nutzerkonto account : nutzerKonten){
            if(account.getVorname().equals(vorname) && account.getNachname().equals(nachname)){
                return true;
            }
        }
        return false;
    }

    /*
        Funktion um einen Nutzer zu erstellen.
        Überprüft, ob der Nutzer bereits existiert, sowie ob der Parameter args erwartete Werte enthält.

        @param args:List<String> Liste der Befehlsargumenten. Erwartet ["", ""] -> Vorname, Nachname
        @return void
    */
    private static void createUser(List<String> args) {
        if(args.size() == 2) {
            if(userExists(args.get(0), args.get(1))){
                p("Nutzer existiert bereits");
                return;
            }
            nutzerKonten.add(new Nutzerkonto(args.get(0), args.get(1)));
            p("Nutzerkonto wurde erstellt. Sie können sich nun anmelden");
        } else {
            p("Nutzer konnte nicht erstellt werden");
        }

    }

    /*
        Funktion um einen Nutzer anzumelden.
        Testet ob args die erwarteten Werte enthält, ob der Nutzer existiert und ob er gelöscht ist. Meldet den Nutzer an.

        @param args:List<String> Befehlsargumente

        @return void
    */
    private static void login(List<String> args) {
        if(args.size() == 2) {
            Nutzerkonto target = null;
            for(Nutzerkonto k : nutzerKonten) {
                if(
                        k.getVorname().equals(args.get(0)) &&
                                k.getNachname().equals(args.get(1)) &&
                                !k.getGeloscht()
                ) {
                    target = k;
                    break;
                }
            }
            if(target != null) {

                currentLog = target;
                p("Anmeldung erfolgreich.");
                loggedIn();

            } else {
                p("Anmeldung fehlgeschlagen.");
            }
        } else {
            p("Anmeldung fehlgeschlagen");
        }


    }

    /*
        Funktion, welche nach dem Anmelden aufgerufen wird.
        Gibt den aktuellen Status aus und verwaltet die Eingabe der Nutzerbefehle

        @return void
    */
    private static void loggedIn() {
        if(currentLog == null) {
            return;
        }
        p("Sie sind angemeldet als " + currentLog.getVorname() + " " + currentLog.getNachname());
        p("Sie haben aktuell " + currentLog.getBucherEntliehen() + " Bücher ausgeliehen");
        p("Sie haben offene Strafzahlungen in Höhe von " + currentLog.getStrafgebuehren() + " offen.");
        p("___________________________________________________________");
        p("");
        while(currentLog != null) {
            pHelp(true);
            List<String> c = parse_input(currentLog.getNachname() + currentLog.getVorname().charAt(0) +"@bibliothek:~$ ");
            String cmd = c.get(0);
            c.remove(0);
            List<String> cmdargs = c;
            // Befehle
            switch(cmd) {
                /*
                    status: Gibt aktuelle Informationen zu dem Nutzer aus
                */
                case "status":
                    p("Ausleihbericht:");
                    p("Sie sind angemeldet als " + currentLog.getVorname() + " " + currentLog.getNachname());
                    p("Sie haben aktuell " + currentLog.getBucherEntliehen() + " Bücher ausgeliehen");
                    p("Sie haben " + currentLog.getUeberzogeneBucher() + " Bücher überzogen.");
                    p("Sie haben offene Strafzahlungen in Höhe von " + currentLog.getStrafgebuehren() + " offen.");
                    break;
                /*
                    delete: Befehl um den Nutzer zu löschen
                */
                case "delete":
                    if(currentLog.getBucherEntliehen() > 0) {
                        p("Sie können das Konto nicht löschen, weil noch Bücher entliehen sind.");
                        break;
                    }
                    if(currentLog.getStrafgebuehren() > 0) {
                        p("Sie können das Konto nicht löschen, weil sie noch offene Strafzahlungen haben.");
                        break;
                    }
                    currentLog.loeschenNutzerkonto();
                    p("Nutzerkonto gelöscht. Sie werden ausgelogt.");
                    currentLog = null;
                    break;
                /*
                    logout: Loggt den Benutzer aus
                */
                case "logout":
                    currentLog = null;
                    break;
                /*
                    ausleihen: Buch ausleihen
                */
                case "ausleihen":
                    if(currentLog.buchAusleihen()) {
                        p("Buch wurde ausgeliehen");
                    } else {
                        p("Buch konnte nicht ausgeliehen werden. Bitte vorher Strafzahlung entrichten.");
                    }
                    break;
                /*
                    zurück: Buch zurückgeben
                */
                case "zurück":
                    if(currentLog.getBucherEntliehen() > 0) {
                        currentLog.buchZurueckgeben();
                        p("Buch zurückgegeben.");
                    } else {
                        p("Sie haben keine Bücher entliehen");
                    }
                    break;
                /*
                    zahlen: Strafzahlungen leisten
                */
                case "zahlen":
                    if(currentLog.getStrafgebuehren() > 0) {
                        if(cmdargs.size() == 1) {
                            try {
                                if(Double.parseDouble(cmdargs.get(0)) == currentLog.getStrafgebuehren()) {
                                    currentLog.strafgebuehrenBezahlen(Double.parseDouble(cmdargs.get(0)));
                                } else {
                                    p("Sie können nur passend zahlen.");
                                }
                            } catch (NumberFormatException e) {
                                p("Ein Fehler ist aufgetreten");
                            }
                        }
                    } else {
                        p("Sie haben keine Strafgebühren.");
                    }
                    break;
                default:
                    p("Befehl wurde nicht gefunden.");
            }
        }
    }

    /*
        Shortcuts zur Kommandozeilen-Ausgabe
    */
    private static void p(String s) {
        System.out.println(s);
    }
    private static void p() {
        System.out.println("");
    }

    /*
        Gibt die Hilfe aus.

        @param loggedIn:boolean Ob der Nutzer eingeloggt ist

        @return void
    */
    private static void pHelp(boolean loggedIn) {
        if(loggedIn) {
            p("Befehle:");
            p(" status - Ausleihstatus");
            p(" delete - Nutzerkonto löschen");
            p(" ausleihen - Buch ausleihen");
            p(" zurück - Buch zurückgeben");
            p(" zahlen <Menge> - Strafgebühren bezahlen");
            p(" logout - Abmelden");
            p();
        } else {
            p("Befehle:");
            p(" create <Vorname> <Nachname> - Nutzerkonto erstellen");
            p(" login <Vorname> <Nachname> - Einloggen");
            p(" shutdown - System herunterfahren");
            p();
        }

    }



    /*
        Funktion um Kommandozeilen-Eingabe auszuwerten.
        Fasst Eingaben in Anführungszeichen zu einem Argument zusammen.

        @param qu:String Eingabe des Nutzers

        @return List<String> Liste mit getrennten eingaben
    */
    private static List<String> parse_input(String qu){
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
}
