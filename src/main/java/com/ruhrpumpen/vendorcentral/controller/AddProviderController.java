package com.ruhrpumpen.vendorcentral.controller;

import com.ruhrpumpen.vendorcentral.data.dao.ListDetailDao;
import com.ruhrpumpen.vendorcentral.model.ListDetail;
import com.ruhrpumpen.vendorcentral.navigation.Navigator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class AddProviderController {

    private final ListDetailDao listDetailDao;

    public AddProviderController() {
        // Instancia del DAO para acceso a la base de datos
        listDetailDao = new ListDetailDao();
    }

    /**
     * Crea un nuevo vendor con datos generados automáticamente y lo guarda en la base de datos.
     */
    public void crearNuevoVendor() {
        // Generar valores únicos y ficticios
        String nuevoVendor = "Vendor-" + UUID.randomUUID().toString().substring(0, 8);
        String nuevoLocation = "Location-" + UUID.randomUUID().toString().substring(0, 8);
        String nuevoPrimaryContact = "Contacto-" + UUID.randomUUID().toString().substring(0, 8);
        String nuevoContactPerson = "Persona-" + UUID.randomUUID().toString().substring(0, 8);
        String nuevoStandard = "Standard-" + UUID.randomUUID().toString().substring(0, 4);
        String nuevoTelephone = "Tel-" + UUID.randomUUID().toString().substring(0, 6);
        String nuevoSecondaryContact = "Sec-" + UUID.randomUUID().toString().substring(0, 8);
        String nuevoSecondaryTelephone = "SecTel-" + UUID.randomUUID().toString().substring(0, 6);
        String nuevoSecondaryEmail = UUID.randomUUID().toString().substring(0, 10) + "@example.com";

        // Crear objeto vendor
        ListDetail nuevoVendorDetalle = new ListDetail(
                nuevoVendor,
                nuevoLocation,
                nuevoPrimaryContact,
                nuevoContactPerson,
                nuevoStandard,
                nuevoTelephone,
                nuevoSecondaryContact,
                nuevoSecondaryTelephone,
                nuevoSecondaryEmail
        );

        // Guardar en base de datos
        listDetailDao.createListDetail(nuevoVendorDetalle);

        System.out.println("Vendor creado: " + nuevoVendor + " en " + nuevoLocation);
    }

    /**
     * Lista todos los vendors existentes en la base de datos.
     */
    public void listarVendors() {
        List<ListDetail> vendors = listDetailDao.getAllListDetails();
        for (ListDetail v : vendors) {
            System.out.println("Vendor: " + v.getVendor() + ", Ubicación: " + v.getLocation());
        }
    }

    /**
     * Borra un vendor dado su nombre y localización.
     */
    public void eliminarVendor(String vendor, String location) {
        listDetailDao.deleteListDetail(vendor, location);
        System.out.println("Vendor eliminado: " + vendor + " en " + location);
    }

    /**
     * Actualiza los datos de un vendor existente.
     */
    public void actualizarVendor(ListDetail vendorActualizado) {
        listDetailDao.updateListDetail(vendorActualizado);
        System.out.println("Vendor actualizado: " + vendorActualizado.getVendor());
    }

    /**
     * Método de navegación a la vista principal.
     */
    public void irAVistaPrincipal() throws IOException {
        Navigator.navigateTo("/com/ruhrpumpen/vendorcentral/view/main-view.fxml");
    }
}
