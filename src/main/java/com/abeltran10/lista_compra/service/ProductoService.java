package com.abeltran10.lista_compra.service;

import com.abeltran10.lista_compra.dao.ProductoDAO;
import com.abeltran10.lista_compra.enumerator.Tipo;
import com.abeltran10.lista_compra.exception.EliminarProductoException;
import com.abeltran10.lista_compra.exception.GuardarProductoException;
import com.abeltran10.lista_compra.model.Producto;
import com.abeltran10.lista_compra.utils.GenerarPDF;

import java.io.IOException;
import java.util.List;

public class ProductoService {

    private final ProductoDAO dao = new ProductoDAO();

    public List<Producto> consultarInventario(Tipo tipo) {
        return dao.findAllByType(tipo.getTipo());
    }

    public List<Producto> consultarStockBajo(Tipo tipo) {
        return dao.filtrarStockBajo(tipo.getTipo());
    }

    public void anyadirProducto(Producto producto) throws GuardarProductoException {
        dao.guardarProducto(producto);
    }

    public void editarProducto(Producto producto) throws GuardarProductoException {
        dao.guardarProducto(producto);
    }

    public void eliminarProducto(Producto producto) throws EliminarProductoException {
        dao.eliminarProducto(producto);
    }

    public List<Producto> obtenerProductosStockBajo() {
        return dao.findAllByStockBajo();
    }

    public void generarListaCompraPDF(List<Producto> productos) throws IOException {
        GenerarPDF generarPDF = new GenerarPDF();
        String html = generarPDF.setTemplateVariables(productos);
        generarPDF.generarPDF(html);
    }
}
