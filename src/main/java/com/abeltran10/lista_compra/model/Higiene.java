package com.abeltran10.lista_compra.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "higiene")
public class Higiene extends Producto {

    private Boolean usoPersonal;

    public Higiene() {
    }

    public Higiene(Integer id) {
        super(id);
    }

    public Higiene(Integer id, String nombre, Double precioMedio, Integer stock, Integer stockLimite, String unidad,
                   LocalDate fechaUltimaCompra, Boolean usoPersonal) {
        super(id, nombre, precioMedio, stock, stockLimite, unidad, fechaUltimaCompra);
        this.usoPersonal = usoPersonal;
    }

    public Boolean getUsoPersonal() {
        return usoPersonal;
    }

    public void setUsoPersonal(Boolean usoPersonal) {
        this.usoPersonal = usoPersonal;
    }
}
