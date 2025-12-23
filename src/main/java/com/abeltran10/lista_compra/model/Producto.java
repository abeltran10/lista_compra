package com.abeltran10.lista_compra.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Double precioMedio;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Integer stockLimite;

    @Column(nullable = false)
    private String unidad;

    private LocalDate fechaUltimaCompra;

    public Producto() {
    }

    public Producto(Integer id) {
        this.id = id;
    }

    public Producto(Integer id, String nombre, Double precioMedio, Integer stock, Integer stockLimite,
                    String unidad, LocalDate fechaUltimaCompra) {
        this.id = id;
        this.nombre = nombre;
        this.precioMedio = precioMedio;
        this.stock = stock;
        this.stockLimite = stockLimite;
        this.unidad = unidad;
        this.fechaUltimaCompra = fechaUltimaCompra;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecioMedio() {
        return precioMedio;
    }

    public void setPrecioMedio(Double precioMedio) {
        this.precioMedio = precioMedio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStockLimite() {
        return stockLimite;
    }

    public void setStockLimite(Integer stockLimite) {
        this.stockLimite = stockLimite;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public LocalDate getFechaUltimaCompra() {
        return fechaUltimaCompra;
    }

    public void setFechaUltimaCompra(LocalDate fechaUltimaCompra) {
        this.fechaUltimaCompra = fechaUltimaCompra;
    }

    public boolean stockBajo() {
        return stock <= stockLimite;
    }
}
