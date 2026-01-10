package com.abeltran10.lista_compra.utils;

import javafx.scene.control.Alert;

public class MensajeAlert {

    public static void confirmacion(String msg) {
        new Alert(Alert.AlertType.CONFIRMATION, msg).showAndWait();
    }

    public static void error(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }

    public static void aviso(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Stock bajo");
        alert.setHeaderText("Productos con stock bajo");

        alert.setContentText(msg);

        alert.showAndWait();
    }
}
