package de.hems.tallerik.wetter;

import java.util.ArrayList;
import java.util.List;

public class Database {
    List<DataSet> data;

    public Database() {
        data = new ArrayList<>();
    }

    public void add(String dt) {
        data.add(new DataSet(dt));
    }

    public DataSet getLast() {
        return data.get(data.size() - 1);
    }

    public List<DataSet> getAll() {
        return data;
    }
}
