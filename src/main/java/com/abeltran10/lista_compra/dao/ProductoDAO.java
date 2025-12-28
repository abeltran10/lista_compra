package com.abeltran10.lista_compra.dao;

import com.abeltran10.lista_compra.model.Producto;
import com.abeltran10.lista_compra.utils.JPA;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ProductoDAO {
    private EntityManager em = JPA.getEntityManager();

    public List<Producto> findAlimentacion() {
        try {
            return em.createQuery(
                    "SELECT p FROM Producto p WHERE TYPE(p) = Alimentacion",
                    Producto.class
            ).getResultList();
        } finally {
            em.close();
        }
    }
}
