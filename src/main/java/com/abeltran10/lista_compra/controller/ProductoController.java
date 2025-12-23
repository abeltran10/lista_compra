package com.abeltran10.lista_compra.controller;

import com.abeltran10.lista_compra.model.Producto;
import com.abeltran10.lista_compra.service.ProductoService;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ProductoController {
    @FXML private TableView<Producto> tablaProductos;
    @FXML private TabPane tabPaneTipos;

    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, Double> colPrecio;
    @FXML private TableColumn<Producto, Integer> colStock;
    @FXML private TableColumn<Producto, String> colInfo;

    private ProductoService service;

    @FXML
    public void initialize() {

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