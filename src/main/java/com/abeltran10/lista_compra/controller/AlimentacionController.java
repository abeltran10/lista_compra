package com.abeltran10.lista_compra.controller;

import com.abeltran10.lista_compra.model.Producto;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AlimentacionController {

    @FXML private TableView<Producto> tablaAlimentacion;

    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, Double> colPrecioMedio;
    @FXML private TableColumn<Producto, Integer> colStock;
    @FXML private TableColumn<Producto, String> colUnidad;
    @FXML private TableColumn<Producto, String> colFechaUltimaCompra;
    @FXML private TableColumn<Producto, String> colFechaCaducidad;

    @FXML
    public void initialize() {

    }


}
