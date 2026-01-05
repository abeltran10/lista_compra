package com.abeltran10.lista_compra.controller.tables;

import com.abeltran10.lista_compra.controller.forms.AlimentacionFormController;
import com.abeltran10.lista_compra.controller.intrfz.ProductoControllerIntrfz;
import com.abeltran10.lista_compra.model.Alimentacion;
import com.abeltran10.lista_compra.model.Producto;
import com.abeltran10.lista_compra.enumerator.Tipo;
import com.abeltran10.lista_compra.service.ProductoService;
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

public class AlimentacionController implements ProductoControllerIntrfz {

    @FXML private TableView<Producto> tablaAlimentacion;

    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, Double> colPrecioMedio;
    @FXML private TableColumn<Producto, Integer> colStock;
    @FXML private TableColumn<Producto, String> colFechaUltimaCompra;
    @FXML private TableColumn<Producto, String> colFechaCaducidad;
    @FXML private TableColumn<Producto, String> colTipo;

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

        colTipo.setCellValueFactory(
                new PropertyValueFactory<>("tipo"));
    }

    private void cargarDatos() {
        List<Producto> productos = service.consultarInventario(Tipo.ALIMENTACION);
        tablaAlimentacion.setItems(FXCollections.observableArrayList(productos));
    }


    @Override
    public void onStockBajo() {
        List<Producto> productos = service.consultarStockBajo(Tipo.ALIMENTACION);
        tablaAlimentacion.setItems(FXCollections.observableArrayList(productos));
    }

    @Override
    public void onVerTodos() {
        cargarDatos();
    }

    @Override
    public void onCrear() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/abeltran10/lista_compra/views/forms/alimentacion-form.fxml")
            );

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Nuevo producto de alimentación");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);

            AlimentacionFormController controller = loader.getController();
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
        Producto producto = tablaAlimentacion.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/abeltran10/lista_compra/views/forms/alimentacion-form.fxml")
            );

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(producto.getNombre());
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);

            AlimentacionFormController controller = loader.getController();
            controller.setProducto((Alimentacion) producto);
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
        Producto producto = tablaAlimentacion.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/abeltran10/lista_compra/views/forms/alimentacion-form.fxml")
            );

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(producto.getNombre());
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);

            AlimentacionFormController controller = loader.getController();
            controller.setProducto((Alimentacion) producto);
            controller.setStage(stage);

            stage.showAndWait();

            Producto productoEditar = controller.getProducto();
            if (productoEditar != null) {
                service.editarProducto(productoEditar);

                cargarDatos();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
