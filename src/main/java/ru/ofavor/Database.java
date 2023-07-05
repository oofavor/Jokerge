package ru.ofavor;

import java.sql.*;

public class Database {
    private Connection connection;
    private static Database instance;

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
            instance.setConnection();
        }
        return instance;
    }

    private void setConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2169_kurs",
                    "std_2169_kurs", "12345678");

            System.out.println("!!!Connected to database");
        } catch (Exception e) {
            System.out.println("!!!Error connecting to the database");
            System.out.println(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
