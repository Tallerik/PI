package de.hems.tallerik.ardudata;

import org.takes.facets.fork.FkRegex;
import org.takes.facets.fork.TkFork;
import org.takes.http.Exit;
import org.takes.http.FtBasic;

import java.io.IOException;

public class Main {

    private static Database db;

    public static void main(String[] args) {
        db = new Database("localhost", 3306, "ardudata", "root", "");

        db.connect();

        CrawlerThread th1 = new CrawlerThread("http://10.140.243.33");

        try {
            new FtBasic(
                    new TkFork(new FkRegex("/", new Web())), 8080
            ).start(Exit.NEVER);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Database getDb() {
        return db;
    }
}