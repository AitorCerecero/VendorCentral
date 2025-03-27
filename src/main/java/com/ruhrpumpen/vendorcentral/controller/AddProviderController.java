package com.ruhrpumpen.vendorcentral.controller;

import com.ruhrpumpen.vendorcentral.navigation.Navigator;
import javafx.fxml.FXML;

import java.io.IOException;

public class AddProviderController {

    @FXML
    public void goToMain() throws IOException {
        Navigator.navigateTo("/com/ruhrpumpen/vendorcentral/view/main-view.fxml");
    }
}
