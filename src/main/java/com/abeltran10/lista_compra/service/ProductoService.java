package com.abeltran10.lista_compra.service;

import com.abeltran10.lista_compra.dao.ProductoDAO;
import com.abeltran10.lista_compra.model.Alimentacion;
import com.abeltran10.lista_compra.model.Producto;

import java.util.List;

public class ProductoService {

    private final ProductoDAO dao = new ProductoDAO();

    public List<Producto> obtenerTodos() {
        return dao.findAlimentacion();
    }
}
