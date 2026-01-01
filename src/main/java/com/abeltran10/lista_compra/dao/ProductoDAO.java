package com.abeltran10.lista_compra.dao;

import com.abeltran10.lista_compra.model.Producto;
import com.abeltran10.lista_compra.utils.JPA;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ProductoDAO {

    public List<Producto> findAllByType(String tipo) {
        EntityManager em = JPA.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT p FROM Producto p WHERE TYPE(p) = " + tipo,
                    Producto.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Producto> filtrarStockBajo(String tipo) {
        EntityManager em = JPA.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT p FROM Producto p WHERE TYPE(p) = " + tipo + " AND p.stock <= p.stockLimite" ,
                    Producto.class
            ).getResultList();
        } finally {
            em.close();
        }
    }
}
