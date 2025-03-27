module com.ruhrpumpen.vendorcentral {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.ruhrpumpen.vendorcentral to javafx.fxml;
    exports com.ruhrpumpen.vendorcentral;
    exports com.ruhrpumpen.vendorcentral.controller;
    opens com.ruhrpumpen.vendorcentral.controller to javafx.fxml;
}