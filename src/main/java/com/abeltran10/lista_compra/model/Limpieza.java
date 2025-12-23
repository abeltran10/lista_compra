package com.abeltran10.lista_compra.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "limpieza")
public class Limpieza extends Producto {

    private Boolean esToxico;

    public Limpieza() {
    }

    public Limpieza(Integer id) {
        super(id);
    }

    public Limpieza(Integer id, String nombre, Double precioMedio, Integer stock, Integer stockLimite, String unidad,
                    LocalDate fechaUltimaCompra, Boolean esToxico) {
        super(id, nombre, precioMedio, stock, stockLimite, unidad, fechaUltimaCompra);
        this.esToxico = esToxico;
    }

    public Boolean isEsToxico() {
        return esToxico;
    }

    public void setEsToxico(Boolean esToxico) {
        this.esToxico = esToxico;
    }
}
