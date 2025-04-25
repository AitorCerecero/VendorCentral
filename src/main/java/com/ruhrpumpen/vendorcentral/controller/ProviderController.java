package com.ruhrpumpen.vendorcentral.controller;

import com.ruhrpumpen.vendorcentral.data.dao.ListDetailDao;
import com.ruhrpumpen.vendorcentral.model.ListDetail;
import com.ruhrpumpen.vendorcentral.navigation.Navigator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProviderController implements Initializable  {

    @FXML private Label vendorLabel;
    @FXML private Label locationLabel;
    @FXML private Label categoryLabel;
    @FXML private Label primaryContact;
    @FXML private Label secondaryContact;
    @FXML private Label phone;
    @FXML private Label email;
    @FXML private Label secondaryPhone;
    @FXML private Label secondaryEmail;
    @FXML private Label comments;

    private String vendor;
    private String location;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vendorLabel.setText(vendor);
        locationLabel.setText(location);

        ListDetailDao listDetailDao = new ListDetailDao(); // Inicializa el ListDetailsDAO

        ListDetail detail = listDetailDao.getListDetail(vendor, location);

        categoryLabel.setText(detail.getStandard());
        primaryContact.setText(detail.getPrimaryContact());
        secondaryContact.setText(detail.getContactPerson());
        phone.setText(detail.getTelephone());
        email.setText(detail.getSecondaryContact());
        secondaryPhone.setText(detail.getSecondaryTelephone());
        secondaryEmail.setText(detail.getSecondaryEmail());
        comments.setText(detail.getComments());
    }

    @FXML
    public void goToMain() throws IOException {
        Navigator.navigateTo("/com/ruhrpumpen/vendorcentral/view/main-view.fxml");
    }

    public void setVendorAndLocation(String vendor, String location) {
        this.vendor = vendor;
        this.location = location;
    }
}
