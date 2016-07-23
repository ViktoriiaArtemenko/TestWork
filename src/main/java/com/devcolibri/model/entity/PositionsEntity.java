package com.devcolibri.model.entity;

import com.devcolibri.model.database.ConnectDataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class PositionsEntity {
    private String query = "SELECT * FROM `positions`";
    private HashMap<String, Integer> rateHour = new HashMap();
    private HashMap<String, Integer> rateFix = new HashMap();
    private ArrayList<String> position = new ArrayList();
    private HashMap<String, String> action = new HashMap();
    private ConnectDataBase connectDataBase = new ConnectDataBase();

    public PositionsEntity() {
        try (Statement statement = connectDataBase.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                position.add(resultSet.getString("position"));
                action.put(resultSet.getString("position"), resultSet.getString("action"));
                rateHour.put(resultSet.getString("position"), resultSet.getInt("rate_hour"));
                rateFix.put(resultSet.getString("position"), resultSet.getInt("rate_fix"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Integer> getRateHour() {
        return rateHour;
    }

    public HashMap<String, Integer> getRateFix() {
        return rateFix;
    }

    public ArrayList<String> getPosition() {
        return position;
    }

    public HashMap<String, String> getAction() {
        return action;
    }
}
