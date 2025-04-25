module com.ruhrpumpen.vendorcentral {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens com.ruhrpumpen.vendorcentral to javafx.fxml;
    exports com.ruhrpumpen.vendorcentral;
    exports com.ruhrpumpen.vendorcentral.controller;
    opens com.ruhrpumpen.vendorcentral.controller to javafx.fxml;
}