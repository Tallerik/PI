package de.hems.tallerik.wetter.client.lib;

import java.util.Date;

public class DataSet {
    private Date date;
    private double temp;
    private int luftft;
    private int wind;
    private int luftdr;
    private double niederschlag;


    public DataSet(Date date, double temp, int luftft, int wind, int luftdr, double niederschlag) {
        this.date = date;
        this.temp = temp;
        this.luftft = luftft;
        this.wind = wind;
        this.luftdr = luftdr;
        this.niederschlag = niederschlag;
    }

    public DataSet(String data) {
        String[] split = data.split(":");
        date = new Date(Long.parseLong(split[0]));
        temp = Double.parseDouble(split[1]);
        luftft = Integer.parseInt(split[2]);
        wind = Integer.parseInt(split[3]);
        luftdr = Integer.parseInt(split[4]);
        niederschlag = Double.parseDouble(split[5]);
    }

    public String toEncodedString() {
        return
                date.getTime() +
                ":" + temp +
                ":" + luftft +
                ":" + wind +
                ":" + luftdr +
                ":" + niederschlag;
    }

    @Override
    public String toString() {
        return "DataSet{" +
                "date=" + date +
                ", temp=" + temp +
                ", luftft=" + luftft +
                ", wind=" + wind +
                ", luftdr=" + luftdr +
                ", niederschlag=" + niederschlag +
                '}';
    }
}
