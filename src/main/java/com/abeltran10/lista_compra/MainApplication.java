package com.abeltran10.lista_compra;

import com.abeltran10.lista_compra.model.Alimentacion;
import com.abeltran10.lista_compra.utils.JPA;
import jakarta.persistence.EntityManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/producto-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Lista de la compra");
        stage.setScene(scene);
        stage.show();
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