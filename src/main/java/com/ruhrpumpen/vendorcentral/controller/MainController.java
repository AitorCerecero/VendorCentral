package com.ruhrpumpen.vendorcentral.controller;

import com.ruhrpumpen.vendorcentral.navigation.Navigator;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainController {
    @FXML
    public void goToAddProvider() throws IOException {
        Navigator.navigateTo("/com/ruhrpumpen/vendorcentral/view/add-provider-view.fxml");
    }
}