package com.abeltran10.lista_compra.controller.tables;


import com.abeltran10.lista_compra.controller.forms.HigieneFormController;
import com.abeltran10.lista_compra.controller.intrfz.ProductoControllerIntrfz;
import com.abeltran10.lista_compra.enumerator.Tipo;
import com.abeltran10.lista_compra.model.Higiene;
import com.abeltran10.lista_compra.model.Producto;
import com.abeltran10.lista_compra.service.ProductoService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HigieneController implements ProductoControllerIntrfz {

    @FXML private TableView<Producto> tablaHigiene;

    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, Double> colPrecioMedio;
    @FXML private TableColumn<Producto, Integer> colStock;
    @FXML private TableColumn<Producto, String> colFechaUltimaCompra;
    @FXML private TableColumn<Producto, Boolean> colUsoPersonal;

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

        colUsoPersonal.setCellValueFactory(
                new PropertyValueFactory<>("usoPersonal"));
    }

    private void cargarDatos() {
        List<Producto> productos = service.consultarInventario(Tipo.HIGIENE);
        tablaHigiene.setItems(FXCollections.observableArrayList(productos));
    }

    public void onStockBajo() {
        List<Producto> productos = service.consultarStockBajo(Tipo.HIGIENE);
        tablaHigiene.setItems(FXCollections.observableArrayList(productos));
    }

    @Override
    public void onVerTodos() {
        cargarDatos();
    }

    @Override
    public void onCrear() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/abeltran10/lista_compra/views/forms/higiene-form.fxml")
            );

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Nuevo producto de higiene");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);

            HigieneFormController controller = loader.getController();
            controller.setStage(stage);

            stage.showAndWait();

            Producto nuevo = controller.getProducto();
            if (nuevo != null) {
                service.anyadirProducto(nuevo);
                cargarDatos();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onVer() {
        Producto producto = tablaHigiene.getSelectionModel().getSelectedItem();
        if (producto == null) return;

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/abeltran10/lista_compra/views/forms/higiene-form.fxml")
            );

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(producto.getNombre());
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);

            HigieneFormController controller = loader.getController();
            controller.setProducto((Higiene) producto);
            controller.disableInputs(true);
            controller.getBtnGuardar().setDisable(true);
            controller.setStage(stage);

            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEditar() {
        Producto producto = tablaHigiene.getSelectionModel().getSelectedItem();
        if (producto == null) return;

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/abeltran10/lista_compra/views/forms/higiene-form.fxml")
            );

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(producto.getNombre());
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);

            HigieneFormController controller = loader.getController();
            controller.setProducto((Higiene) producto);
            controller.setStage(stage);

            stage.showAndWait();

            Producto editado = controller.getProducto();
            if (editado != null) {
                service.editarProducto(editado);
                cargarDatos();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEliminar() {
        Producto producto = tablaHigiene.getSelectionModel().getSelectedItem();
        if (producto == null) return;

        service.eliminarProducto(producto);
        cargarDatos();
        mostrarConfirmacion("Producto eliminado con éxito.");
    }

    private void mostrarConfirmacion(String msg) {
        new Alert(Alert.AlertType.CONFIRMATION, msg).showAndWait();
    }
}

