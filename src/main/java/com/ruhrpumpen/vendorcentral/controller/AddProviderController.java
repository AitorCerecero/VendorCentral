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

public class AddProviderController implements Initializable {

    private ListDetailDao listDetailDao;

    @FXML
    public void goToMain() throws IOException {
        Navigator.navigateTo("/com/ruhrpumpen/vendorcentral/view/main-view.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listDetailDao = new ListDetailDao(); // Inicializa el ListDetailsDAO

        // Crear un nuevo ListDetails cada vez que se inicializa el controlador
        crearNuevoListDetails();
    }

    private void crearNuevoListDetails() {
        // Generar valores de ejemplo para el nuevo ListDetails
        String nuevoVendor = "Vendor-" + UUID.randomUUID().toString().substring(0, 8);
        String nuevaLocation = "Location-" + UUID.randomUUID().toString().substring(0, 8);
        String nuevoPrimaryContact = "Contacto-" + UUID.randomUUID().toString().substring(0, 8);
        String nuevoContactPerson = "Persona-" + UUID.randomUUID().toString().substring(0, 8);
        String nuevoStandard = "Standard-" + UUID.randomUUID().toString().substring(0, 4);
        String nuevoTelephone = "Tel-" + UUID.randomUUID().toString().substring(0, 6);
        String nuevoSecondaryContact = "Sec-" + UUID.randomUUID().toString().substring(0, 8);
        String nuevoSecondaryTelephone = "SecTel-" + UUID.randomUUID().toString().substring(0, 6);
        String nuevoSecondaryEmail = UUID.randomUUID().toString().substring(0, 10) + "@example.com";

        // Crear un nuevo objeto ListDetails
        ListDetail nuevoDetalle = new ListDetail(
                nuevoVendor,
                nuevaLocation,
                nuevoPrimaryContact,
                nuevoContactPerson,
                nuevoStandard,
                nuevoTelephone,
                nuevoSecondaryContact,
                nuevoSecondaryTelephone,
                nuevoSecondaryEmail
        );

        // Guardar el nuevo ListDetails en la base de datos
        listDetailDao.createListDetail(nuevoDetalle);

        System.out.println("Nuevo ListDetails creado para Vendor: " + nuevoVendor + ", Location: " + nuevaLocation);
        // O podrías mostrar una notificación al usuario
    }
}
