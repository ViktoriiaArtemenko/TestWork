package com.devcolibri.model.modules;

import com.devcolibri.model.database.ConnectWithDataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DataBaseModule {
    public static final String QUERY_DELETE_COLLABORATORS = "UPDATE `collaborators` SET salary=0, hours=0 WHERE id=?";
    public static final String QUERY_DELETE_FREELANCERS = "UPDATE `freelancers` SET salary=0 WHERE id=?";

    public static final String QUERY_UPDATE_FREELANCERS = "UPDATE `freelancers` SET salary=? WHERE id=?";
    public static final String QUERY_UPDATE_COLLABORATORS_HOUR = "UPDATE `collaborators` SET  hours=? WHERE id=?";
    public static final String QUERY_UPDATE_COLLABORATORS_SALARY = "UPDATE `collaborators` SET  salary=? WHERE id=?";

    public static final String QUERY_SELECT_FREELANCERS = "SELECT * FROM `freelancers`";
    public static final String QUERY_SELECT_COLLABORATORS = "SELECT * FROM `collaborators`";
    public static final String QUERY_SELECT_POSITIONS = "SELECT * FROM `positions`";

    public static final String QUERY_SELECT_FREELANCERS_SALARY = "SELECT salary FROM `freelancers` WHERE id=?";
    public static final String QUERY_SELECT_COLLABORATORS_SALARY = "SELECT salary FROM `collaborators` WHERE id=?";
    public static final String QUERY_SELECT_COLLABORATORS_HOURS = "SELECT hours FROM `collaborators` WHERE id=?";

    private ConnectWithDataBase connectWithDataBase = new ConnectWithDataBase();

    public DataBaseModule() {
    }

    public void deleteData(String query, int size) {
        try (PreparedStatement preparedStatement = connectWithDataBase.getConnection().prepareStatement(query)) {
            for (int i = 1; i <= size; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.executeUpdate();
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDataBase(String query, int field, int id) {
        try (PreparedStatement preparedStatement = connectWithDataBase.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, field);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readDataBaseNameId(String query, LinkedHashMap<String, Integer> id, Collection name) {
        try (Statement statement = connectWithDataBase.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                id.put(resultSet.getString("name"), resultSet.getInt("id"));
                name.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int readDataBaseSalary(String query, int id) {
        int field = 0;
        try (PreparedStatement preparedStatement = connectWithDataBase.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                field = res.getInt("salary");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return field;
    }

    public int readDataBaseHours(int id) {
        int field = 0;
        try (PreparedStatement preparedStatement = connectWithDataBase.getConnection().prepareStatement
                (QUERY_SELECT_COLLABORATORS_HOURS)) {
            preparedStatement.setInt(1, id);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                field = res.getInt("hours");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return field;
    }

    public void readDataBasePositionsTable(LinkedHashMap<String, Integer> rateHour, LinkedHashMap<String, Integer>
            rateFix, ArrayList<String> position, HashMap<String, String> action) {
        try (Statement statement = connectWithDataBase.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(QUERY_SELECT_POSITIONS);
            while (resultSet.next()) {
                position.add(resultSet.getString("position"));
                action.put(resultSet.getString("position"), resultSet.getString("action"));
                rateHour.put(resultSet.getString("action"), resultSet.getInt("rate_hour"));
                rateFix.put(resultSet.getString("position"), resultSet.getInt("rate_fix"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
