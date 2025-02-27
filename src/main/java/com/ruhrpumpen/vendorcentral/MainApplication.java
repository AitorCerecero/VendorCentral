package com.ruhrpumpen.vendorcentral;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.net.URL;
import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        URL iconUrl = getClass().getResource("/com/ruhrpumpen/vendorcentral/RP ICON.png");
        stage.getIcons().add(new Image(iconUrl.toExternalForm()));
        stage.setTitle("VendorCentral");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}