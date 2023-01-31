package de.hems.tallerik.ardudata;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.Map;

public class Database {
    private String url;
    private String user;
    private String pass;

    private Connection con;

    public Database(String host, int port, String database, String user, String pass) {
        this.url = "jdbc:mysql://"+host+":"+port+"/"+database;
        this.user = user;
        this.pass = pass;
    }

    public boolean connect() {

        try {
            con = DriverManager.getConnection(url, user, pass);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void insertData(int source, int[] data) {
        try {
            PreparedStatement st = con.prepareStatement("INSERT INTO data (source, data1,data2,data3,data4,data5,data6) VALUES (?,?,?,?,?,?,?)");
            st.setInt(1, source);
            st.setInt(2, data[0]);
            st.setInt(3, data[1]);
            st.setInt(4, data[2]);
            st.setInt(5, data[3]);
            st.setInt(6, data[4]);
            st.setInt(7, data[5]);
            st.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getAllData() {
        try {
            Statement stm = con.createStatement();

            ResultSet rs = stm.executeQuery("SELECT * FROM data;");
            JSONArray ar = new JSONArray();
            while(rs.next()) {
                JSONObject entry = new JSONObject();
                entry.put("source", rs.getInt("source"));
                for (int i = 0; i < 6; i++) {
                    entry.put(String.valueOf(i), rs.getInt("data"+(i+1)));
                }
                ar.put(entry);
            }
            JSONObject fin = new JSONObject();
            fin.put("data", ar);
            return fin.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }


}
