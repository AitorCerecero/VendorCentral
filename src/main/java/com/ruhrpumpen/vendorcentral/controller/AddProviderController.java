package com.ruhrpumpen.vendorcentral.controller;

import com.ruhrpumpen.vendorcentral.data.dao.ListDetailDao;
import com.ruhrpumpen.vendorcentral.model.ListDetail;
import com.ruhrpumpen.vendorcentral.navigation.Navigator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProviderController implements Initializable {

    public Button addProviderButton;
    private ListDetailDao listDetailDao;

    @FXML private TextField providerNameField;
    @FXML private TextField categoryField;
    @FXML private TextField locationField;
    @FXML private TextField primaryContactField;
    @FXML private TextField secondaryContactField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private TextField secondaryPhoneField;
    @FXML private TextField secondaryEmailField;
    @FXML private TextArea commentsField;

    @FXML private Label errorLabel;

    @FXML
    public void goToMain() throws IOException {
        Navigator.navigateTo("/com/ruhrpumpen/vendorcentral/view/main-view.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listDetailDao = new ListDetailDao(); // Inicializa el ListDetailsDAO
    }

    @FXML
    private void onAddProviderClick() {
        String name = providerNameField.getText();
        String category = categoryField.getText();
        String location = locationField.getText();
        String primaryContact = primaryContactField.getText();
        String secondaryContact = secondaryContactField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();
        String secondaryPhone = secondaryPhoneField.getText();
        String secondaryEmail = secondaryEmailField.getText();
        String comments = commentsField.getText();

        if (name.isBlank() || location.isBlank() || category.isBlank()) {
            errorLabel.setText("El nombre, categoria y localizacion son obligatorios.");
            return;
        }

        errorLabel.setText(""); // Limpiar errores

        ListDetail nuevoProveedor = new ListDetail(
                name,
                location,
                primaryContact,
                secondaryContact,
                category,
                phone,
                email,
                secondaryEmail,
                secondaryPhone,
                comments
        );

        listDetailDao.createListDetail(nuevoProveedor);

        System.out.println("Proveedor agregado: " + name);

        // Limpia los campos
        providerNameField.clear();
        categoryField.clear();
        locationField.clear();
        primaryContactField.clear();
        secondaryContactField.clear();
        phoneField.clear();
        emailField.clear();
        secondaryPhoneField.clear();
        secondaryEmailField.clear();
        commentsField.clear();
    }
}
