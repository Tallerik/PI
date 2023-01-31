package de.hems.tallerik.ardudata;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Arrays;

public class CrawlerThread extends Thread {

    private String url;

    public CrawlerThread(String url) {
        this.url = url;
        start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                String data = IOUtils.toString(URI.create(url), Charset.defaultCharset());

                System.out.println(data);
                JSONObject jsonObject = new JSONObject(data);
                JSONObject obj = jsonObject.getJSONObject("data");
                int id = jsonObject.getInt("id");
                int[] out = new int[6];
                for (int i = 0; i < 6; i++) {
                    out[i] = Integer.parseInt(obj.getString(String.valueOf(i)));
                }
                System.out.println(Arrays.toString(out));
                System.out.println(id);
                Main.getDb().insertData(id, out);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
