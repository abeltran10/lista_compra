package com.abeltran10.lista_compra.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPA {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("listaCompra");

    private JPA() {
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void close() {
        emf.close();
    }
}
