package com.abeltran10.lista_compra.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "Higiene")
public class Higiene extends Producto {

    @Column(nullable = false)
    private String usoPersonal;

    public Higiene() {
    }

    public Higiene(Integer id) {
        super(id);
    }

    public Higiene(Integer id, String nombre, Double precioMedio, Integer stock, Integer stockLimite,
                   LocalDate fechaUltimaCompra, String usoPersonal) {
        super(id, nombre, precioMedio, stock, stockLimite, fechaUltimaCompra);
        this.usoPersonal = usoPersonal;
    }

    public String getUsoPersonal() {
        return usoPersonal;
    }

    public void setUsoPersonal(String usoPersonal) {
        this.usoPersonal = usoPersonal;
    }

    public boolean isUsoPersonal() {
        return "S".equals(usoPersonal);
    }
}
