package com.abeltran10.lista_compra.controller;

import com.abeltran10.lista_compra.model.Producto;
import com.abeltran10.lista_compra.service.ProductoService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListaCompraController {
    @FXML
    private TableView<Producto> tablaLista;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, Integer> colStock;

    private List<Producto> productos = new ArrayList<>();

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public List<Producto> getProductos() {
        return this.productos;
    }

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        // Esto permite que la celda se convierta en un TextField al editar
        colNombre.setCellFactory(TextFieldTableCell.forTableColumn());

        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        // Para números es similar, pero requiere un convertidor
        colStock.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        tablaLista.setEditable(true);

        colNombre.setOnEditCommit(event -> {
            Producto p = event.getRowValue();
            p.setNombre(event.getNewValue());
        });

        colStock.setOnEditCommit(event -> {
            Producto p = event.getRowValue();
            p.setStock(event.getNewValue());
        });
    }

    @FXML
    public void onAnyadir() {
        // 1. Crear un nuevo objeto vacío
        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre("Producto");
        nuevoProducto.setStock(1);

        productos.add(nuevoProducto);

        tablaLista.getItems().add(nuevoProducto);

        int fila = tablaLista.getItems().size() - 1;

        tablaLista.getSelectionModel().select(fila);
        tablaLista.scrollTo(nuevoProducto);

        tablaLista.edit(fila, colNombre);
    }

    @FXML
    public void onEditar() {
        int fila = tablaLista.getSelectionModel().getSelectedIndex();

        if (fila >= 0) {
            tablaLista.requestFocus();

            tablaLista.edit(fila, colNombre);
        }
    }

    @FXML
    public void onEliminar() {
        int fila = tablaLista.getSelectionModel().getSelectedIndex();

        if (fila >= 0) {
            Producto producto = tablaLista.getSelectionModel().getSelectedItem();

            tablaLista.getItems().remove(fila);
            productos.remove(producto);

            tablaLista.getSelectionModel().clearSelection();
        }
    }

    @FXML
    public void onCancelar() {
        this.stage.close();
    }

    @FXML
    public void onGuardar() {
       this.productos = tablaLista.getItems();

       this.stage.close();
    }
}
