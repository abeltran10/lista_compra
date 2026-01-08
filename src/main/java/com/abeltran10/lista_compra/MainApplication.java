package com.abeltran10.lista_compra;

import com.abeltran10.lista_compra.model.Alimentacion;
import com.abeltran10.lista_compra.model.Producto;
import com.abeltran10.lista_compra.service.ProductoService;
import com.abeltran10.lista_compra.utils.JPA;
import jakarta.persistence.EntityManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class MainApplication extends Application {

    private ProductoService productoService = new ProductoService();

    @Override
    public void start(Stage stage) throws IOException {
        mostrarVentanaPrincipal(stage);
        comprobarStockBajo();


    }

    private void mostrarVentanaPrincipal(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/producto-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 960, 700);
        stage.setTitle("Lista de la compra");
        stage.setScene(scene);
        stage.show();
    }

    private void comprobarStockBajo() {
        List<Producto> bajos = productoService.obtenerProductosStockBajo();

        if (!bajos.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Stock bajo");
            alert.setHeaderText("Productos con stock bajo");

            String contenido = bajos.stream()
                    .map(p -> "- " + p.getNombre() + " (" + p.getStock() + ")")
                    .collect(Collectors.joining("\n"));

            alert.setContentText(contenido);
            alert.showAndWait();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();

        // Aquí cerramos el EntityManagerFactory al salir
        JPA.close();
    }

    public static void main(String[] args) {
        launch();
    }
}