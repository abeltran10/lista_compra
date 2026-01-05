package com.abeltran10.lista_compra.controller.forms;

import com.abeltran10.lista_compra.model.Alimentacion;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AlimentacionFormController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtPrecioMedio;
    @FXML private Spinner<Integer> spStock;
    @FXML private Spinner<Integer> spStockLimite;
    @FXML private DatePicker dpFechaUltimaCompra;
    @FXML private DatePicker dpFechaCaducidad;
    @FXML private TextField txtTipo;


    private Stage stage;
    private Alimentacion producto;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Alimentacion getProducto() {
        return producto;
    }

    @FXML
    public void initialize() {
        spStock.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 999, 1)
        );

        spStockLimite.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 999, 1)
        );
    }

    @FXML
    private void onGuardar() {
        try {
            producto = new Alimentacion();
            producto.setNombre(txtNombre.getText());
            producto.setPrecioMedio(Double.parseDouble(txtPrecioMedio.getText()));
            producto.setStock(spStock.getValue());
            producto.setStockLimite(spStockLimite.getValue());
            producto.setFechaUltimaCompra(dpFechaUltimaCompra.getValue());
            producto.setFechaCaducidad(dpFechaCaducidad.getValue());
            producto.setTipo(txtTipo.getText());

            stage.close();
        } catch (Exception e) {
            mostrarError("Datos incorrectos");
        }
    }

    @FXML
    private void onCancelar() {
        producto = null;
        stage.close();
    }

    private void mostrarError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }
}
