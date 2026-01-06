package com.abeltran10.lista_compra.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "Alimentacion")
public class Alimentacion extends Producto{

    @Column(nullable = false)
    private LocalDate fechaCaducidad;

    @Column(nullable = false)
    private String tipo;

    public Alimentacion() {
    }

    public Alimentacion(Integer id) {
        super(id);
    }

    public Alimentacion(Integer id, String nombre, Double precioMedio, Integer stock, Integer stockLimite,
                        LocalDate fechaUltimaCompra, LocalDate fechaCaducidad, String tipo) {
        super(id, nombre, precioMedio, stock, stockLimite, fechaUltimaCompra);
        this.fechaCaducidad = fechaCaducidad;
        this.tipo = tipo;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
