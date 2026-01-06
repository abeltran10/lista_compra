package com.abeltran10.lista_compra.dao;

import com.abeltran10.lista_compra.model.Producto;
import com.abeltran10.lista_compra.utils.JPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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

    public void guardarProducto(Producto producto) {
        EntityManager em = JPA.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            if (producto.getId() == null) {
                em.persist(producto);
            } else {
                em.merge(producto);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }

    }

    public void eliminarProducto(Producto producto) {
        EntityManager em = JPA.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            producto = em.find(Producto.class, producto.getId());
            em.remove(producto);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }

    }
}
