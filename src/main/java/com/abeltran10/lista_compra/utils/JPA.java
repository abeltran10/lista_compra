package com.abeltran10.lista_compra.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class JPA {

    private static EntityManagerFactory emf;

    private JPA() {
    }

    private static void inicializar() {
        String userHome = System.getProperty("user.home");
        File dir = new File(userHome, ".lista_compra");
        dir.mkdirs();

        String dbPath = dir.getAbsolutePath() + "/lista_compra.db";

        Map<String, Object> props = new HashMap<>();
        props.put("jakarta.persistence.jdbc.url", "jdbc:sqlite:" + dbPath);

        emf = Persistence.createEntityManagerFactory("listaCompra", props);
    }

    public static EntityManager getEntityManager() {
        if (emf == null) {
            inicializar();
        }
        return emf.createEntityManager();
    }

    public static void close() {
        if (emf != null) {
            emf.close();
        }
    }
}
