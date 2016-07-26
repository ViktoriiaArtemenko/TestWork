package com.devcolibri.model.entities;

import com.devcolibri.model.database.ConnectDataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeSet;

public class FreelancersEntity {
    private String query = "SELECT * FROM `freelancers`";
    private String[] nameArray;
    private TreeSet<String> name = new TreeSet();
    private ConnectDataBase connectDataBase = new ConnectDataBase();

    public FreelancersEntity() {
        try (Statement statement = connectDataBase.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        nameArray = name.toArray(new String[name.size()]);
    }

    public TreeSet getName() {
        return name;
    }

    public String[] getNameArray() {
        return nameArray;
    }
}
