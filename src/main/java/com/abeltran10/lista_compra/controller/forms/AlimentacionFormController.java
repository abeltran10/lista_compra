package com.abeltran10.lista_compra.controller.forms;

import com.abeltran10.lista_compra.model.Alimentacion;
import com.abeltran10.lista_compra.model.Producto;
import com.abeltran10.lista_compra.utils.MensajeAlert;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AlimentacionFormController {

    @FXML private Button btnGuardar;
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

    public Button getBtnGuardar() {
        return this.btnGuardar;
    }

    public Alimentacion getProducto() {
        return producto;
    }

    public void setProducto(Alimentacion producto) {
        this.producto = producto;
        cargarDatos();
    }

    public void disableInputs(boolean disable) {
        txtNombre.setDisable(disable);
        txtPrecioMedio.setDisable(disable);
        spStock.setDisable(disable);
        spStockLimite.setDisable(disable);
        dpFechaUltimaCompra.setDisable(disable);
        dpFechaCaducidad.setDisable(disable);
        txtTipo.setDisable(disable);
    }

    @FXML
    public void initialize() {
        spStock.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 999, 1)
        );

        spStockLimite.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999, 1)
        );
    }

    private void cargarDatos() {
        if (producto == null) return;

        txtNombre.setText(producto.getNombre());
        txtPrecioMedio.setText(String.valueOf(producto.getPrecioMedio()));
        spStock.getValueFactory().setValue(producto.getStock());
        spStockLimite.getValueFactory().setValue(producto.getStockLimite());
        dpFechaUltimaCompra.setValue(producto.getFechaUltimaCompra());
        dpFechaCaducidad.setValue(producto.getFechaCaducidad());
        txtTipo.setText(producto.getTipo());
    }

    @FXML
    private void onGuardar() {
        try {
            if (producto == null) producto = new Alimentacion();

            producto.setNombre(txtNombre.getText());
            producto.setPrecioMedio(Double.parseDouble(txtPrecioMedio.getText()));
            producto.setStock(spStock.getValue());
            producto.setStockLimite(spStockLimite.getValue());
            producto.setFechaUltimaCompra(dpFechaUltimaCompra.getValue());
            producto.setFechaCaducidad(dpFechaCaducidad.getValue());
            producto.setTipo(txtTipo.getText());

            stage.close();
        } catch (Exception e) {
            MensajeAlert.error("Datos incorrectos");
        }
    }

    @FXML
    private void onCancelar() {
        stage.close();
    }

}
