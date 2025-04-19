package com.ruhrpumpen.vendorcentral;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {


    private static Connection connection;

    public static Connection connect() {
        String Conn = "jdbc:sqlserver://AITORPC\\SQLEXPRESS;databaseName=FireDB;user=FireDB;password=FireDB;";

        try {
            connection = DriverManager.getConnection(Conn);
            System.out.println("Connection Established");
        } catch (SQLException ex) {
            System.out.println("Connection Failed");
            throw new RuntimeException(ex);
        }
        return connection;
    }

    public void initialize() {
        connect();
    }

    public Connection getConnection() {
        return connection;
    }
}
