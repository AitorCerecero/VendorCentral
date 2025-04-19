package com.ruhrpumpen.vendorcentral.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:vendorcentral.db";
    private static Connection connection;
    private static boolean initialized = false;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL);
            if (!initialized) {
                createTables(); //  Creamos las tablas si es que no estan inicializadas
                initialized = true;
            }
        }
        return connection;
    }

    private static void createTables() throws SQLException {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            // Ejecutar las sentencias CREATE TABLE IF NOT EXISTS para cada tabla
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Divisions (divisionName TEXT PRIMARY KEY)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS VendorCategory (categoryName TEXT PRIMARY KEY)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Vendor (vendorName TEXT PRIMARY KEY)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Locations (locationName TEXT PRIMARY KEY, projectBiddingLocation TEXT, coveredCountries TEXT)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS VendorDivisions (vendorName TEXT, divisionName TEXT, PRIMARY KEY (vendorName, divisionName), FOREIGN KEY (vendorName) REFERENCES Vendor(vendorName), FOREIGN KEY (divisionName) REFERENCES Divisions(divisionName))");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS VendorCategoryVendors (categoryName TEXT, vendorName TEXT, PRIMARY KEY (categoryName, vendorName), FOREIGN KEY (categoryName) REFERENCES VendorCategory(categoryName), FOREIGN KEY (vendorName) REFERENCES Vendor(vendorName))");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ListDetails (vendor TEXT, location TEXT, primaryContact TEXT, contactPerson TEXT, standard TEXT, telephone TEXT, secondaryContact TEXT, secondaryTelephone TEXT, secondaryEmail TEXT, PRIMARY KEY (vendor, location), FOREIGN KEY (vendor) REFERENCES Vendor(vendorName), FOREIGN KEY (location) REFERENCES Locations(locationName))");

            System.out.println("Tablas creadas o ya existentes.");

        } catch (SQLException e) {
            System.err.println("Error al crear tablas: " + e.getMessage());
            throw new SQLException("Error al inicializar la base de datos.", e); // Lanza una excepción para indicar fallo en la inicialización
        }
    }

    // Método para cerrar la conexión cuando tu aplicación termine
    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Conexión a la base de datos cerrada.");
        }
    }
}
