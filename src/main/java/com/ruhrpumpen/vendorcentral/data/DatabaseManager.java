package com.ruhrpumpen.vendorcentral.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:vendorcentral.db";
    private static Connection connection;
    private static boolean initialized = false;

    public static void setTestConnection(Connection conn) {
        connection = conn;
        try {
            System.out.println(">>> setTestConnection: Test connection set (" + conn.getMetaData().getURL() + ").");
        } catch (SQLException e) {
            System.err.println(">>> setTestConnection: Error getting URL for test connection: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            // Verifica si es la conexión de memoria o la de archivo
            String url = connection.getMetaData().getURL();
            if (url.equals("jdbc:sqlite::memory:")) {
                System.out.println(">>> getConnection: Returning test (in-memory) connection.");
            } else {
                System.out.println(">>> getConnection: Returning production DB connection (" + url + ").");
            }
            return connection;
        }

        System.out.println(">>> getConnection: Connection is null or closed. Attempting to open production DB: " + DB_URL);
        connection = DriverManager.getConnection(DB_URL);

        if (!initialized) {
            System.out.println(">>> getConnection: Production DB not initialized. Calling createTables().");
            try {
                // ¡Pasa la conexión como argumento! NO llames a getConnection() aquí.
                createTables(connection);
                initialized = true; // Solo marca como inicializado si la creación fue exitosa
                System.out.println(">>> getConnection: Production DB initialized successfully.");
            } catch (SQLException e) {
                System.err.println(">>> getConnection: Failed to initialize production DB tables.");
                // Es buena práctica cerrar la conexión si la inicialización falla
                try {
                    connection.close();
                } catch (SQLException closeEx) {
                    System.err.println(">>> getConnection: Error closing connection after failed table creation: " + closeEx.getMessage());
                }
                connection = null; // Asegúrate de que la conexión estática sea null si falla
                throw e; // Relanza la excepción para indicar el fallo
            }
        } else {
            System.out.println(">>> getConnection: Production DB already initialized.");
        }

        String url = connection.getMetaData().getURL();
        System.out.println(">>> getConnection: Returning newly opened connection (" + url + ").");
        return connection;
    }

    private static void createTables(Connection conn) throws SQLException {
        // Usa la conexión que fue pasada como parámetro
        // Usa try-with-resources SÓLO para el Statement
        try (Statement stmt = conn.createStatement()) {
            // Ejecutar las sentencias CREATE TABLE IF NOT EXISTS para cada tabla
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Divisions (divisionName TEXT PRIMARY KEY)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS VendorCategory (categoryName TEXT PRIMARY KEY)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Vendor (vendorName TEXT PRIMARY KEY)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Locations (locationName TEXT PRIMARY KEY, projectBiddingLocation TEXT, coveredCountries TEXT)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS VendorDivisions (vendorName TEXT, divisionName TEXT, PRIMARY KEY (vendorName, divisionName), FOREIGN KEY (vendorName) REFERENCES Vendor(vendorName), FOREIGN KEY (divisionName) REFERENCES Divisions(divisionName))");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS VendorCategoryVendors (categoryName TEXT, vendorName TEXT, PRIMARY KEY (categoryName, vendorName), FOREIGN KEY (categoryName) REFERENCES VendorCategory(categoryName), FOREIGN KEY (vendorName) REFERENCES Vendor(vendorName))");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ListDetails (vendor TEXT, location TEXT, primaryContact TEXT, contactPerson TEXT, standard TEXT, telephone TEXT, secondaryContact TEXT, secondaryTelephone TEXT, secondaryEmail TEXT, comments TEXT, PRIMARY KEY (vendor, location), FOREIGN KEY (vendor) REFERENCES Vendor(vendorName), FOREIGN KEY (location) REFERENCES Locations(locationName))");

            System.out.println("Tablas creadas o ya existentes.");

        } catch (SQLException e) {
            System.err.println("Error al crear tablas: " + e.getMessage());
            // Aquí relanzamos la excepción original, no la envolvemos en una nueva para no perder la causa raíz.
            throw e; // Lanza la excepción original para que la atrape getConnection
        }
        // El Statement se cierra automáticamente aquí. La conexión 'conn' (que es la estática) NO se cierra aquí.
    }

    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            try {
                System.out.println(">>> closeConnection: Closing connection (" + connection.getMetaData().getURL() + ").");
                connection.close();
            } catch (SQLException e) {
                System.err.println(">>> closeConnection: Error closing connection: " + e.getMessage());
                throw e; // Re-lanzar la excepción si ocurre
            }
        } else {
            System.out.println(">>> closeConnection: No connection to close.");
        }
        connection = null; // Opcional pero útil para asegurarse de que la próxima llamada a getConnection no encuentre una conexión cerrada
        initialized = false; // También útil para reseteo completo, pero impacta la lógica de createTables para la DB real.
    }
}
