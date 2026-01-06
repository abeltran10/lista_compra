package com.abeltran10.lista_compra.controller.forms;

import com.abeltran10.lista_compra.model.Higiene;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class HigieneFormController {

    @FXML private Button btnGuardar;

    @FXML private TextField txtNombre;
    @FXML private TextField txtPrecioMedio;
    @FXML private Spinner<Integer> spStock;
    @FXML private Spinner<Integer> spStockLimite;
    @FXML private DatePicker dpFechaUltimaCompra;
    @FXML private CheckBox chkUsoPersonal;

    private Stage stage;
    private Higiene producto;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Button getBtnGuardar() {
        return btnGuardar;
    }

    public Higiene getProducto() {
        return producto;
    }

    public void setProducto(Higiene producto) {
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
        chkUsoPersonal.setSelected(producto.isUsoPersonal());
    }

    @FXML
    private void onGuardar() {
        try {
            if (producto == null) producto = new Higiene();

            producto.setNombre(txtNombre.getText());
            producto.setPrecioMedio(Double.parseDouble(txtPrecioMedio.getText()));
            producto.setStock(spStock.getValue());
            producto.setStockLimite(spStockLimite.getValue());
            producto.setFechaUltimaCompra(dpFechaUltimaCompra.getValue());
            producto.setUsoPersonal(chkUsoPersonal.isSelected() ? "S" : "N");

            stage.close();
        } catch (Exception e) {
            mostrarError("Datos incorrectos");
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
        chkUsoPersonal.setDisable(disable);
    }

    private void mostrarError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }
}
