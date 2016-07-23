package com.devcolibri.model.entity;

import com.devcolibri.model.database.ConnectDataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

public class CollaboratorsEntity {
    private String query = "SELECT * FROM `collaborators`";
    private HashSet<String> name = new HashSet();
    private ConnectDataBase connectDataBase = new ConnectDataBase();

    public CollaboratorsEntity() {
        try (Statement statement = connectDataBase.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name.add(resultSet.getString("name"));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public HashSet getName() {
        return name;
    }

}
