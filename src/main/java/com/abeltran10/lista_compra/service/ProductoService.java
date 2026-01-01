package com.abeltran10.lista_compra.service;

import com.abeltran10.lista_compra.dao.ProductoDAO;
import com.abeltran10.lista_compra.model.Alimentacion;
import com.abeltran10.lista_compra.model.Producto;
import com.abeltran10.lista_compra.num.Tipo;

import java.util.List;

public class ProductoService {

    private final ProductoDAO dao = new ProductoDAO();

    public List<Producto> obtenerTodos(Tipo tipo) {
        return dao.findAllByType(tipo.getTipo());
    }

    public List<Producto> filtrarStockBajo(Tipo tipo) {
        return dao.filtrarStockBajo(tipo.getTipo());
    }
}
