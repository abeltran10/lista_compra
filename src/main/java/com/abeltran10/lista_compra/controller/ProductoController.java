package com.abeltran10.lista_compra.controller;

import com.abeltran10.lista_compra.model.Producto;
import com.abeltran10.lista_compra.service.ProductoService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class ProductoController {

    @FXML private TabPane tabPaneTipos;
    @FXML private StackPane contenedorTabla;

    private ProductoService service;

    @FXML
    public void initialize() {
        cargarTabla("/com/abeltran10/lista_compra/views/tablas/alimentacion-view.fxml");

        tabPaneTipos.getSelectionModel().selectedIndexProperty()
                .addListener((obs, oldVal, newVal) -> {
                    switch (newVal.intValue()) {
                        case 0 -> cargarTabla("/com/abeltran10/lista_compra/views/tablas/alimentacion-view.fxml");
                        case 1 -> cargarTabla("/com/abeltran10/lista_compra/views/tablas/limpieza-view.fxml");
                        case 2 -> cargarTabla("/com/abeltran10/lista_compra/views/tablas/higiene-view.fxml");
                        case 3 -> cargarTabla("/com/abeltran10/lista_compra/views/tablas/otros-view.fxml");
                    }
                });
    }

    private void cargarTabla(String fxml) {
        try {
            Parent tabla = FXMLLoader.load(getClass().getResource(fxml));
            contenedorTabla.getChildren().setAll(tabla);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onGuardar() {}

    @FXML
    public void onCrear() {}

    @FXML
    public void onEditar() {}

    @FXML
    public void onEliminar() {}
}