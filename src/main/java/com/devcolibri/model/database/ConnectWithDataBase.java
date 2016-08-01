package com.devcolibri.model.database;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

public class ConnectWithDataBase {
    private final String URL = "jdbc:mysql://localhost:3306/collaborators";
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    private Connection connection;

    public ConnectWithDataBase() {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                //System.out.println("Соединение с БД установлено.");
            }
        } catch (SQLException e) {
            System.out.println("Не удалось загрузить класс драйвера!");
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
