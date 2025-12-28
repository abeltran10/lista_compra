package com.abeltran10.lista_compra.controller;

import com.abeltran10.lista_compra.model.Alimentacion;
import com.abeltran10.lista_compra.model.Producto;
import com.abeltran10.lista_compra.service.ProductoService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class AlimentacionController {

    @FXML private TableView<Producto> tablaAlimentacion;

    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, Double> colPrecioMedio;
    @FXML private TableColumn<Producto, Integer> colStock;
    @FXML private TableColumn<Producto, String> colUnidad;
    @FXML private TableColumn<Producto, String> colFechaUltimaCompra;
    @FXML private TableColumn<Producto, String> colFechaCaducidad;

    private final ProductoService service = new ProductoService();

    @FXML
    public void initialize() {
        configurarColumnas();
        cargarDatos();
    }

    private void configurarColumnas() {
        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre"));

        colPrecioMedio.setCellValueFactory(
                new PropertyValueFactory<>("precioMedio"));

        colStock.setCellValueFactory(
                new PropertyValueFactory<>("stock"));

        colUnidad.setCellValueFactory(
                new PropertyValueFactory<>("unidad"));

        colFechaUltimaCompra.setCellValueFactory(
                new PropertyValueFactory<>("fechaUltimaCompra"));

        colFechaCaducidad.setCellValueFactory(
                new PropertyValueFactory<>("fechaCaducidad"));
    }

    private void cargarDatos() {
        List<Producto> productos = service.obtenerTodos();
        tablaAlimentacion.setItems(FXCollections.observableArrayList(productos));
    }


}
