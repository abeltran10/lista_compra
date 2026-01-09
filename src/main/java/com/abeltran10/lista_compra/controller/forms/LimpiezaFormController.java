package com.abeltran10.lista_compra.controller.forms;

import com.abeltran10.lista_compra.model.Limpieza;
import com.abeltran10.lista_compra.utils.MensajeAlert;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LimpiezaFormController {

    @FXML private Button btnGuardar;

    @FXML private TextField txtNombre;
    @FXML private TextField txtPrecioMedio;
    @FXML private Spinner<Integer> spStock;
    @FXML private Spinner<Integer> spStockLimite;
    @FXML private DatePicker dpFechaUltimaCompra;
    @FXML private CheckBox chkEsToxico;

    private Stage stage;
    private Limpieza producto;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Button getBtnGuardar() {
        return btnGuardar;
    }

    public Limpieza getProducto() {
        return producto;
    }

    public void setProducto(Limpieza producto) {
        this.producto = producto;
        cargarDatos();
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
        chkEsToxico.setSelected(producto.isEsToxico());
    }

    @FXML
    private void onGuardar() {
        try {
            if (producto == null) producto = new Limpieza();

            producto.setNombre(txtNombre.getText());
            producto.setPrecioMedio(Double.parseDouble(txtPrecioMedio.getText()));
            producto.setStock(spStock.getValue());
            producto.setStockLimite(spStockLimite.getValue());
            producto.setFechaUltimaCompra(dpFechaUltimaCompra.getValue());
            producto.setEsToxico(chkEsToxico.isSelected() ? "S" : "N");

            stage.close();
        } catch (NumberFormatException | NullPointerException e) {
            MensajeAlert.error("El valor del campo \"Precio medio\" debe ser un número con los decimales separados por \".\"");
            producto = null;
        }
    }

    @FXML
    private void onCancelar() {
        stage.close();
    }

    public void disableInputs(boolean disable) {
        txtNombre.setDisable(disable);
        txtPrecioMedio.setDisable(disable);
        spStock.setDisable(disable);
        spStockLimite.setDisable(disable);
        dpFechaUltimaCompra.setDisable(disable);
        chkEsToxico.setDisable(disable);
    }

}
