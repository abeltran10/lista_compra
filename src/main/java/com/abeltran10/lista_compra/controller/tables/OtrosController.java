package com.abeltran10.lista_compra.controller.tables;

import com.abeltran10.lista_compra.controller.forms.OtrosFormController;
import com.abeltran10.lista_compra.controller.intrfz.ProductoControllerIntrfz;
import com.abeltran10.lista_compra.enumerator.Tipo;
import com.abeltran10.lista_compra.exception.EliminarProductoException;
import com.abeltran10.lista_compra.exception.GuardarProductoException;
import com.abeltran10.lista_compra.model.Otros;
import com.abeltran10.lista_compra.model.Producto;
import com.abeltran10.lista_compra.service.ProductoService;
import com.abeltran10.lista_compra.utils.MensajeAlert;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class OtrosController implements ProductoControllerIntrfz {

    @FXML private TableView<Producto> tablaOtros;

    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, Double> colPrecioMedio;
    @FXML private TableColumn<Producto, Integer> colStock;
    @FXML private TableColumn<Producto, String> colFechaUltimaCompra;
    @FXML private TableColumn<Producto, String> colDescripcion;

    private final ProductoService service = new ProductoService();

    @FXML
    public void initialize() {
        configurarColumnas();
        cargarDatos();
    }

    private void configurarColumnas() {
        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );

        colPrecioMedio.setCellValueFactory(
                new PropertyValueFactory<>("precioMedio")
        );

        colStock.setCellValueFactory(
                new PropertyValueFactory<>("stock")
        );

        colFechaUltimaCompra.setCellValueFactory(
                new PropertyValueFactory<>("fechaUltimaCompra")
        );

        colDescripcion.setCellValueFactory(
                new PropertyValueFactory<>("descripcion")
        );
    }

    private void cargarDatos() {
        List<Producto> productos = service.consultarInventario(Tipo.OTROS);
        tablaOtros.setItems(FXCollections.observableArrayList(productos));
    }

    @Override
    public void onStockBajo() {
        List<Producto> productos = service.consultarStockBajo(Tipo.OTROS);
        tablaOtros.setItems(FXCollections.observableArrayList(productos));
    }

    @Override
    public void onVerTodos() {
        cargarDatos();
    }

    @Override
    public void onCrear() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/abeltran10/lista_compra/views/forms/otros-form.fxml")
            );

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Nuevo producto");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);

            OtrosFormController controller = loader.getController();
            controller.setStage(stage);

            stage.showAndWait();

            Producto nuevo = controller.getProducto();
            if (nuevo != null) {
                service.anyadirProducto(nuevo);
                cargarDatos();
            }

        } catch (IOException e) {
            MensajeAlert.error("Error inesperado.");
        } catch (GuardarProductoException e) {
            MensajeAlert.error(e.getMessage());
        }
    }

    @Override
    public void onVer() {
        Producto producto = tablaOtros.getSelectionModel().getSelectedItem();
        if (producto == null) return;

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/abeltran10/lista_compra/views/forms/otros-form.fxml")
            );

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(producto.getNombre());
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);

            OtrosFormController controller = loader.getController();
            controller.setProducto((Otros) producto);
            controller.disableInputs(true);
            controller.getBtnGuardar().setDisable(true);
            controller.setStage(stage);

            stage.showAndWait();

        } catch (IOException e) {
            MensajeAlert.error("Error inesperado.");
        }
    }

    @Override
    public void onEditar() {
        Producto producto = tablaOtros.getSelectionModel().getSelectedItem();
        if (producto == null) return;

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/abeltran10/lista_compra/views/forms/otros-form.fxml")
            );

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(producto.getNombre());
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);

            OtrosFormController controller = loader.getController();
            controller.setProducto((Otros) producto);
            controller.setStage(stage);

            stage.showAndWait();

            Producto editado = controller.getProducto();
            if (editado != null) {
                service.editarProducto(editado);
                cargarDatos();
            }

        } catch (IOException e) {
            MensajeAlert.error("Error inesperado.");
        } catch (GuardarProductoException e) {
            MensajeAlert.error(e.getMessage());
        }
    }

    @Override
    public void onEliminar() {
        Producto producto = tablaOtros.getSelectionModel().getSelectedItem();
        if (producto == null) return;

        try {
            service.eliminarProducto(producto);

            cargarDatos();
            MensajeAlert.confirmacion("Producto eliminado con éxito.");
        } catch (EliminarProductoException e) {
            MensajeAlert.error(e.getMessage());
        }

    }
}
