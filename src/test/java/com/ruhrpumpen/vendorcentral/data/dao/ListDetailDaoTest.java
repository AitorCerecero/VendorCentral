package com.ruhrpumpen.vendorcentral.data.dao;

import com.ruhrpumpen.vendorcentral.data.DatabaseManager;
import com.ruhrpumpen.vendorcentral.model.ListDetail;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListDetailDaoTest {
    private ListDetailDao dao;

    // Preparacion de la DB antes de cada test
    @BeforeEach
    void setUp() throws Exception {
        Connection memoryDb = DriverManager.getConnection("jdbc:sqlite::memory:");
        DatabaseManager.setTestConnection(memoryDb);

        try (Statement stmt = memoryDb.createStatement()) {
            stmt.execute("""
            CREATE TABLE ListDetails (
                vendor TEXT NOT NULL,
                location TEXT NOT NULL,
                primaryContact TEXT,
                contactPerson TEXT,
                standard TEXT,
                telephone TEXT,
                secondaryContact TEXT,
                secondaryTelephone TEXT,
                secondaryEmail TEXT,
                comments TEXT,
                PRIMARY KEY (vendor, location)
            )
        """);
        }

        dao = new ListDetailDao();
    }

    @Test
    void crearYObtenerUnDetalleDeberiaRetornarRegistroIngresado() {
        ListDetail detail = new ListDetail(
                "VendorA", "LocationA", "Aitor", "Cerecero",
                "ISO9005", "123456", "Ricardo", "654321", "ricardo@example.com", "Example"
        );

        dao.createListDetail(detail);
        ListDetail result = dao.getListDetail("VendorA", "LocationA");

        assertNotNull(result);
        assertEquals("Aitor", result.getPrimaryContact());
        assertEquals("ISO9005", result.getStandard());
    }

    @Test
    void actualizarRegistoDebeModificarElRegistro() {
        ListDetail detail = new ListDetail("VendorB", "LocationB", "A", "B", "C", "D", "E", "F", "G", "");
        dao.createListDetail(detail);

        detail.setPrimaryContact("RicardoDB");
        dao.updateListDetail(detail);

        ListDetail updated = dao.getListDetail("VendorB", "LocationB");
        assertEquals("RicardoDB", updated.getPrimaryContact());
    }

    @Test
    void borrarRegistroDebeQuitarloDeLaDB() {
        ListDetail detail = new ListDetail("VendorX", "LocationY", "A", "B", "C", "D", "E", "F", "G", "");
        dao.createListDetail(detail);

        dao.deleteListDetail("VendorX", "LocationY");
        assertNull(dao.getListDetail("VendorX", "LocationY"));
    }

    @Test
    void obtenerTodosLosRegistrosDebeRetornarMultiplesRegistrosgetAllListDetails_shouldReturnMultipleRecords() {
        dao.createListDetail(new ListDetail("Vendor1", "Loc1", "A", "B", "C", "D", "E", "F", "G", ""));
        dao.createListDetail(new ListDetail("Vendor2", "Loc2", "X", "Y", "Z", "W", "V", "U", "T", ""));
        dao.createListDetail(new ListDetail("Vendor3", "Loc3", "X", "Y", "Z", "W", "V", "U", "T", ""));

        List<ListDetail> list = dao.getAllListDetails();

        assertEquals(3, list.size());
    }

    // Despues de cada test cerramos la conexion de la DB
    @AfterEach
    void tearDown() throws Exception {
        DatabaseManager.closeConnection();
    }
}
