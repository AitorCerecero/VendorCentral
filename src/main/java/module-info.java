module com.ruhrpumpen.vendorcentral {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.ruhrpumpen.vendorcentral to javafx.fxml;
    exports com.ruhrpumpen.vendorcentral;
}