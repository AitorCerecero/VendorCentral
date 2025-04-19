package com.ruhrpumpen.vendorcentral;

import com.ruhrpumpen.vendorcentral.data.DatabaseManager;
import com.ruhrpumpen.vendorcentral.navigation.Navigator;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.io.IOException;
import java.sql.SQLException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            // Inicializar la base de datos al inicio de la aplicación
            DatabaseManager.getConnection();
            System.out.println("Base de datos inicializada.");

            // Establecer la referencia del Stage principal a Navigator
            Navigator.setMainStage(stage);

            // Agregar ícono al Stage (opcional)
            URL iconUrl = getClass().getResource("/com/ruhrpumpen/vendorcentral/assets/rp/RP ICON.png");
            assert iconUrl != null;
            stage.getIcons().add(new Image(iconUrl.toExternalForm()));

            // Establecer el título de la ventana
            stage.setTitle("VendorCentral");

            // Establecer el tamaño de la ventana
            stage.setWidth(800);   // Ancho fijo
            stage.setHeight(600);  // Alto fijo

            // Cargar la pantalla inicial
            Navigator.navigateTo("/com/ruhrpumpen/vendorcentral/view/main-view.fxml");

            // Mostrar la ventana
            stage.show();

        } catch (SQLException e) {
            System.err.println("Error al inicializar la base de datos: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}