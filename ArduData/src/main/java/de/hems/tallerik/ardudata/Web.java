package de.hems.tallerik.ardudata;

import org.takes.Request;
import org.takes.Response;
import org.takes.Take;
import org.takes.rs.RsText;

public class Web implements Take {

    @Override
    public Response act(Request request) {

       return new RsText(Main.getDb().getAllData());
    }
}
