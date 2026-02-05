package com.abeltran10.lista_compra.controller;

import com.abeltran10.lista_compra.controller.forms.AlimentacionFormController;
import com.abeltran10.lista_compra.controller.intrfz.ProductoControllerIntrfz;
import com.abeltran10.lista_compra.model.Alimentacion;
import com.abeltran10.lista_compra.model.Producto;
import com.abeltran10.lista_compra.service.ProductoService;
import com.abeltran10.lista_compra.utils.MensajeAlert;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductoController {

    @FXML private TabPane tabPaneTipos;
    @FXML private StackPane contenedorTabla;

    private Map<Tab, ProductoControllerIntrfz> controllersPorTab = new HashMap<>();

    private ProductoService service = new ProductoService();

    @FXML
    public void initialize() {
        cargarTabla(tabPaneTipos.getTabs().get(0), "/com/abeltran10/lista_compra/views/tables/alimentacion-view.fxml");

        tabPaneTipos.getSelectionModel().selectedIndexProperty()
                .addListener((obs, oldVal, newVal) -> {
                    switch (newVal.intValue()) {
                        case 0 -> cargarTabla(tabPaneTipos.getTabs().get(0), "/com/abeltran10/lista_compra/views/tables/alimentacion-view.fxml");
                        case 1 -> cargarTabla(tabPaneTipos.getTabs().get(1), "/com/abeltran10/lista_compra/views/tables/limpieza-view.fxml");
                        case 2 -> cargarTabla(tabPaneTipos.getTabs().get(2), "/com/abeltran10/lista_compra/views/tables/higiene-view.fxml");
                        case 3 -> cargarTabla(tabPaneTipos.getTabs().get(3), "/com/abeltran10/lista_compra/views/tables/otros-view.fxml");
                    }
                });
    }

    private void cargarTabla(Tab tab, String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent vista = loader.load();

            ProductoControllerIntrfz controller = loader.getController();
            controllersPorTab.put(tab, controller);

            contenedorTabla.getChildren().setAll(vista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onStockBajo() {
        Tab tabSeleccionada = tabPaneTipos.getSelectionModel().getSelectedItem();
        ProductoControllerIntrfz controller = controllersPorTab.get(tabSeleccionada);

        if (controller != null) {
            controller.onStockBajo();
        }
    }

    @FXML
    public void onVerTodos() {
        Tab tabSeleccionada = tabPaneTipos.getSelectionModel().getSelectedItem();
        ProductoControllerIntrfz controller = controllersPorTab.get(tabSeleccionada);

        if (controller != null) {
            controller.onVerTodos();
        }
    }

    @FXML
    public void onGuardar() {}

    @FXML
    public void onCrear() {
        Tab tabSeleccionada = tabPaneTipos.getSelectionModel().getSelectedItem();
        ProductoControllerIntrfz controller = controllersPorTab.get(tabSeleccionada);

        if (controller != null) {
            controller.onCrear();
        }
    }

    @FXML
    public void onEditar() {
        Tab tabSeleccionada = tabPaneTipos.getSelectionModel().getSelectedItem();
        ProductoControllerIntrfz controller = controllersPorTab.get(tabSeleccionada);

        if (controller != null) {
            controller.onEditar();
        }
    }

    @FXML
    public void onVer() {
        Tab tabSeleccionada = tabPaneTipos.getSelectionModel().getSelectedItem();
        ProductoControllerIntrfz controller = controllersPorTab.get(tabSeleccionada);

        if (controller != null) {
            controller.onVer();
        }
    }

    @FXML
    public void onEliminar() {
        Tab tabSeleccionada = tabPaneTipos.getSelectionModel().getSelectedItem();
        ProductoControllerIntrfz controller = controllersPorTab.get(tabSeleccionada);

        if (controller != null) {
            controller.onEliminar();
        }
    }

    @FXML
    public void onAbrirLista() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/abeltran10/lista_compra/views/forms/lista-compra.fxml")
            );

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Lista de la compra");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);

            ListaCompraController controller = loader.getController();
            controller.setStage(stage);

            stage.showAndWait();

            List<Producto> productos = controller.getProductos();

            if (!productos.isEmpty()) {
                service.generarListaCompraPDF(productos);

                MensajeAlert.confirmacion("Lista de la compra guardada con éxito.");
            }

        } catch (IOException e) {
            MensajeAlert.error("Error inesperado.");
        }
    }
}