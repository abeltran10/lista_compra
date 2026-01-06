package com.abeltran10.lista_compra.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "Limpieza")
public class Limpieza extends Producto {

    @Column(nullable = false)
    private String esToxico;

    public Limpieza() {
    }

    public Limpieza(Integer id) {
        super(id);
    }

    public Limpieza(Integer id, String nombre, Double precioMedio, Integer stock, Integer stockLimite,
                    LocalDate fechaUltimaCompra, String esToxico) {
        super(id, nombre, precioMedio, stock, stockLimite, fechaUltimaCompra);
        this.esToxico = esToxico;
    }

    public String getEsToxico() {
        return esToxico;
    }

    public void setEsToxico(String esToxico) {
        this.esToxico = esToxico;
    }

    public boolean isEsToxico() {
        return "S".equals(esToxico);
    }
}
