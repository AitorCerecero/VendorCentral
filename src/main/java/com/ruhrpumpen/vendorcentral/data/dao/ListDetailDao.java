package com.ruhrpumpen.vendorcentral.data.dao;

import com.ruhrpumpen.vendorcentral.data.DatabaseManager;
import com.ruhrpumpen.vendorcentral.model.ListDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) para la clase {@link ListDetail}.
 * Proporciona métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * en la tabla ListDetails de la base de datos SQLite.
 */
public class ListDetailDao {

    /**
     * Crea un nuevo registro de ListDetails en la base de datos.
     *
     * @param listDetails El objeto {@link ListDetail} que contiene los datos a insertar.
     */
    public void createListDetail(ListDetail listDetails) {
        String sql = "INSERT INTO ListDetails (vendor, location, primaryContact, contactPerson, standard, telephone, secondaryContact, secondaryTelephone, secondaryEmail) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn; // Declara la conexión, no la cierres aquí
        try {
            conn = DatabaseManager.getConnection(); // Obtiene la conexión (la estática, manejada por DatabaseManager)
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) { // try-with-resources solo para el Statement
                pstmt.setString(1, listDetails.getVendor());
                pstmt.setString(2, listDetails.getLocation());
                pstmt.setString(3, listDetails.getPrimaryContact());
                pstmt.setString(4, listDetails.getContactPerson());
                pstmt.setString(5, listDetails.getStandard());
                pstmt.setString(6, listDetails.getTelephone());
                pstmt.setString(7, listDetails.getSecondaryContact());
                pstmt.setString(8, listDetails.getSecondaryTelephone());
                pstmt.setString(9, listDetails.getSecondaryEmail());
                pstmt.executeUpdate();
            } // El PreparedStatement se cierra aquí automáticamente
        } catch (SQLException e) {
            System.err.println("Error al crear ListDetails: " + e.getMessage());
        }
    }

    /**
     * Obtiene un registro de ListDetails de la base de datos basado en su vendor y location.
     *
     * @param vendor   El nombre del vendor (parte de la clave primaria).
     * @param location La ubicación (parte de la clave primaria).
     * @return Un objeto {@link ListDetail} si se encuentra un registro coincidente, null en caso contrario.
     */
    public ListDetail getListDetail(String vendor, String location) {
        String sql = "SELECT vendor, location, primaryContact, contactPerson, standard, telephone, secondaryContact, secondaryTelephone, secondaryEmail, comments FROM ListDetails WHERE vendor = ? AND location = ?";
        Connection conn; // Declara la conexión, no la cierres aquí
        try {
            conn = DatabaseManager.getConnection(); // Obtiene la conexión
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) { // try-with-resources para Statement
                pstmt.setString(1, vendor);
                pstmt.setString(2, location);
                try (ResultSet rs = pstmt.executeQuery()) { // try-with-resources para ResultSet
                    if (rs.next()) {
                        return new ListDetail(
                                rs.getString("vendor"),
                                rs.getString("location"),
                                rs.getString("primaryContact"),
                                rs.getString("contactPerson"),
                                rs.getString("standard"),
                                rs.getString("telephone"),
                                rs.getString("secondaryContact"),
                                rs.getString("secondaryTelephone"),
                                rs.getString("secondaryEmail"),
                                rs.getString("comments")
                        );
                    }
                } // ResultSet se cierra aquí
            } // PreparedStatement se cierra aquí
        } catch (SQLException e) {
            System.err.println("Error al obtener ListDetails: " + e.getMessage());
            // throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Actualiza un registro existente de ListDetails en la base de datos.
     * La actualización se realiza basándose en los valores de vendor y location del objeto proporcionado.
     *
     * @param listDetails El objeto {@link ListDetail} con los datos actualizados.
     */
    public void updateListDetail(ListDetail listDetails) {
        String sql = "UPDATE ListDetails SET primaryContact = ?, contactPerson = ?, standard = ?, telephone = ?, secondaryContact = ?, secondaryTelephone = ?, secondaryEmail = ?, comments = ? WHERE vendor = ? AND location = ?";
        Connection conn; // Declara la conexión
        try {
            conn = DatabaseManager.getConnection(); // Obtiene la conexión
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) { // try-with-resources para Statement
                pstmt.setString(1, listDetails.getPrimaryContact());
                pstmt.setString(2, listDetails.getContactPerson());
                pstmt.setString(3, listDetails.getStandard());
                pstmt.setString(4, listDetails.getTelephone()); // Índice 4 para telephone
                pstmt.setString(5, listDetails.getSecondaryContact()); // Índice 5 para secondaryContact
                pstmt.setString(6, listDetails.getSecondaryTelephone()); // Índice 6 para secondaryTelephone
                pstmt.setString(7, listDetails.getSecondaryEmail()); // Índice 7 para secondaryEmail
                pstmt.setString(8, listDetails.getComments()); // Índice 8 para comments
                pstmt.setString(9, listDetails.getVendor()); // Índice 9 para vendor (WHERE clause)
                pstmt.setString(10, listDetails.getLocation()); // Índice 10 para location (WHERE clause)
                pstmt.executeUpdate();
            } // El PreparedStatement se cierra aquí
        } catch (SQLException e) {
            System.err.println("Error al actualizar ListDetails: " + e.getMessage());
        }
    }

    /**
     * Elimina un registro de ListDetails de la base de datos basado en su vendor y location.
     *
     * @param vendor   El nombre del vendor (parte de la clave primaria del registro a eliminar).
     * @param location La ubicación (parte de la clave primaria del registro a eliminar).
     */
    public void deleteListDetail(String vendor, String location) {
        String sql = "DELETE FROM ListDetails WHERE vendor = ? AND location = ?";
        Connection conn; // Declara la conexión
        try {
            conn = DatabaseManager.getConnection(); // Obtiene la conexión
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) { // try-with-resources para Statement
                pstmt.setString(1, vendor);
                pstmt.setString(2, location);
                pstmt.executeUpdate();
            } // PreparedStatement se cierra aquí
        } catch (SQLException e) {
            System.err.println("Error al eliminar ListDetails: " + e.getMessage());
        }
    }

    /**
     * Obtiene todos los registros de ListDetails disponibles en la base de datos.
     *
     * @return Una lista de objetos {@link ListDetail}, o una lista vacía si no hay registros.
     */
    public List<ListDetail> getAllListDetails() {
        List<ListDetail> detalles = new ArrayList<>();
        String sql = "SELECT vendor, location, primaryContact, contactPerson, standard, telephone, secondaryContact, secondaryTelephone, secondaryEmail, comments FROM ListDetails";
        Connection conn; // Declara la conexión
        try {
            conn = DatabaseManager.getConnection(); // Obtiene la conexión
            try (PreparedStatement pstmt = conn.prepareStatement(sql); // try-with-resources para Statement y ResultSet
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ListDetail detalle = new ListDetail(
                            rs.getString("vendor"),
                            rs.getString("location"),
                            rs.getString("primaryContact"),
                            rs.getString("contactPerson"),
                            rs.getString("standard"),
                            rs.getString("telephone"),
                            rs.getString("secondaryContact"),
                            rs.getString("secondaryTelephone"),
                            rs.getString("secondaryEmail"),
                            rs.getString("comments")
                    );
                    detalles.add(detalle);
                }
            } // PreparedStatement y ResultSet se cierran aquí
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los ListDetails: " + e.getMessage());
        }
        return detalles;
    }
}
