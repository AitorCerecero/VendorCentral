package com.ruhrpumpen.vendorcentral.navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Navigator {
    private static Stage mainStage;

    public static void setMainStage(Stage stage) {
        mainStage = stage;
    }

    public static void navigateTo(String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Navigator.class.getResource(fxmlPath)));
        mainStage.setScene(new Scene(root));
        mainStage.show();
    }

    public static void navigateToWithController(String fxmlPath, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(fxmlPath));
        loader.setLocation(Objects.requireNonNull(Navigator.class.getResource(fxmlPath)));
        loader.setController(controller);
        Parent root = loader.load();
        mainStage.setScene(new Scene(root));
        mainStage.show();
    }
}
