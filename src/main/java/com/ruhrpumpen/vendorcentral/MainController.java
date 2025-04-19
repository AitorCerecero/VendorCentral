package com.ruhrpumpen.vendorcentral;

import javafx.fxml.FXML;
//import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Objects;

import java.sql.Connection;
import java.util.Objects;

public class MainController {

    @FXML
    ImageView logo;
    Image myLogo  = new Image(Objects.requireNonNull(getClass().getResourceAsStream("RP LOGO.png")));

    public void initialize() {
        Connection conn = connection.connect(); // usar la clase externa
    }

    public void displayImage(){
        logo.setImage(myLogo);
    }





}