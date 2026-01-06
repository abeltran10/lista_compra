package com.abeltran10.lista_compra.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "Otros")
public class Otros extends Producto {

    @Column(nullable = false)
    private String descripcion;

    protected Otros() {
    }

    public Otros(Integer id) {
        super(id);
    }

    public Otros(Integer id, String nombre, Double precioMedio, Integer stock, Integer stockLimite,
                 LocalDate fechaUltimaCompra, String descripcion) {
        super(id, nombre, precioMedio, stock, stockLimite, fechaUltimaCompra);
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
