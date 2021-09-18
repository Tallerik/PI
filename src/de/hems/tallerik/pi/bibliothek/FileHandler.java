package de.hems.tallerik.pi.bibliothek;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
    Klasse um die Konfig-Datei zu verwalten.
*/
public class FileHandler {
    File config;

    /*
        Konstruktor

        @param path:String Pfad zur Config-Datei
    */
    public FileHandler(String path) {
        config = new File(path);
    }

    /*
        Testet ob Config-Datei existiert

        @return boolean Gibt true zurück, wenn die Datei existiert und diese explizit eine Datei ist.
    */
    public boolean fileExists() {
        return config.exists() && config.isFile();
    }

    /*
        Erstellt die Datei und füllt sie mit Standardwerten

        @return void
    */
    public void create() {
        try {
            config.createNewFile();
            JSONObject o = new JSONObject();
            o.put("data", new JSONArray());
            FileUtils.writeStringToFile(config, o.toString(), Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        Liest die Datei aus. Wandelt in ein JSONObject um und gibt die Nutzerkonto-Liste zurück

        @return List<Nutzerkonto> Liste der ausgelesenen Nutzerkonten
    */
    public List<Nutzerkonto> getData() {
        List<Nutzerkonto> list = new ArrayList<>();
        JSONObject obj;
        try {
            obj = new JSONObject(FileUtils.readFileToString(config, Charset.forName("UTF-8")));
            JSONArray arr = obj.getJSONArray("data");

            for (int i=0; i < arr.length(); i++) {
                if(App.ID_INDEX < arr.getJSONObject(i).getInt("id")) {
                    App.ID_INDEX = arr.getJSONObject(i).getInt("id");
                }
                list.add(new Nutzerkonto(arr.getJSONObject(i)));
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
        Speichert die Nutzerkonten in der JSON-Datei ab.

        @param Liste an Nutzerkonten

        @return void
    */
    public void saveData(List<Nutzerkonto> l) {
        JSONArray arr = new JSONArray();
        for (Nutzerkonto entry : l){
            arr.put(entry.getAsJSON());
        }
        JSONObject data = new JSONObject();
        data.put("data", arr);


        FileWriter writer;
        try {
            writer = new FileWriter(config);
            writer.write(data.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}