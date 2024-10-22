package com.es.stockcontrol.service;

import com.es.stockcontrol.model.Producto;
import com.es.stockcontrol.repository.ProductoRepository;
import java.util.List;

public class ProductoService {

    private final ProductoRepository productoRepository = new ProductoRepository();


    public Producto createProducto(Producto producto) {
        return productoRepository.createProducto(producto);
    }


    public Producto getProductoById(String id) {

        return productoRepository.getProductoById(id);
    }


    public List<Producto> getProductosByStock(boolean stockDisponible) {
        return productoRepository.getProductoByStock(stockDisponible);
    }


    public boolean deleteProductoById(String id) {
        return productoRepository.deleteProductoById(id);
    }


    public Producto updateNombreProducto(String id, String nuevoNombre) {
        return productoRepository.updateNombreProducto(id, nuevoNombre);
    }


    public Producto updateStockProducto(String id, int nuevoStock) {
        return productoRepository.updateStockProducto(id, nuevoStock);
    }


    private String generarProductoId(Producto producto) {
        String categoria = producto.getCategoria().substring(0, 3);
        String nombre = producto.getNombre().substring(0, 3);
        String proveedor = producto.getProveedor().getNombre().substring(0, 3);

        return categoria + nombre + proveedor;
    }
}
