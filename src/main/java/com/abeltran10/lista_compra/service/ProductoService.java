package com.abeltran10.lista_compra.service;

import com.abeltran10.lista_compra.dao.ProductoDAO;
import com.abeltran10.lista_compra.model.Producto;
import com.abeltran10.lista_compra.enumerator.Tipo;

import java.util.List;

public class ProductoService {

    private final ProductoDAO dao = new ProductoDAO();

    public List<Producto> consultarInventario(Tipo tipo) {
        return dao.findAllByType(tipo.getTipo());
    }

    public List<Producto> consultarStockBajo(Tipo tipo) {
        return dao.filtrarStockBajo(tipo.getTipo());
    }

    public void anyadirProducto(Producto producto) {
        dao.guardarProducto(producto);
    }

    public void editarProducto(Producto producto) {
        dao.guardarProducto(producto);
    }
}
