package com.ruhrpumpen.vendorcentral.controller;

import com.ruhrpumpen.vendorcentral.data.dao.ListDetailDao;
import com.ruhrpumpen.vendorcentral.model.ListDetail;
import com.ruhrpumpen.vendorcentral.navigation.Navigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TableView<ListDetail> mainTable;

    @FXML
    private TableColumn<ListDetail, String> vendor;
    @FXML
    private TableColumn<ListDetail, String> location;

    private ObservableList<ListDetail> listDetailsList = FXCollections.observableArrayList();
    private ListDetailDao listDetailDao = new ListDetailDao();

    @FXML
    public void goToAddProvider() throws IOException {
        Navigator.navigateTo("/com/ruhrpumpen/vendorcentral/view/add-provider-view.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configurar las columnas para que muestren los datos del modelo ListDetails
        // El String dentro de PropertyValueFactory debe coincidir con el nombre del atributo en tu clase ListDetails (con el getter correspondiente)
        vendor.setCellValueFactory(new PropertyValueFactory<>("vendor"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));

        // Cargar los datos desde la base de datos y agregarlos a la ObservableList
        cargarDatosTabla();

        // Enlazar la ObservableList con la TableView
        mainTable.setItems(listDetailsList);
    }

    private void cargarDatosTabla() {
        List<ListDetail> todosLosDetalles = listDetailDao.getAllListDetails();
        listDetailsList.addAll(todosLosDetalles);
    }
}