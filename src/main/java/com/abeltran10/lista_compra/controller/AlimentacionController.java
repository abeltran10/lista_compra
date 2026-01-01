package com.abeltran10.lista_compra.controller;

import com.abeltran10.lista_compra.controller.intrf.ProductoControllerIntrfz;
import com.abeltran10.lista_compra.model.Producto;
import com.abeltran10.lista_compra.num.Tipo;
import com.abeltran10.lista_compra.service.ProductoService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class AlimentacionController implements ProductoControllerIntrfz {

    @FXML private TableView<Producto> tablaAlimentacion;

    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, Double> colPrecioMedio;
    @FXML private TableColumn<Producto, Integer> colStock;
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

        colFechaUltimaCompra.setCellValueFactory(
                new PropertyValueFactory<>("fechaUltimaCompra"));

        colFechaCaducidad.setCellValueFactory(
                new PropertyValueFactory<>("fechaCaducidad"));
    }

    private void cargarDatos() {
        List<Producto> productos = service.obtenerTodos(Tipo.ALIMENTACION);
        tablaAlimentacion.setItems(FXCollections.observableArrayList(productos));
    }


    @Override
    public void onStockBajo() {
        List<Producto> productos = service.filtrarStockBajo(Tipo.ALIMENTACION);
        tablaAlimentacion.setItems(FXCollections.observableArrayList(productos));
    }

    @Override
    public void onVerTodos() {
        cargarDatos();
    }
}
