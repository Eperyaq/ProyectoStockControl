package com.es.stockcontrol.service;

import com.es.stockcontrol.model.Producto;

public class ProductoService {

    public String generarProductoId(Producto producto) {
        String categoria = producto.getCategoria().substring(0, 3);
        String nombre = producto.getNombre().substring(0, 3);
        String proveedor = producto.getProveedor().getNombre().substring(0, 3);

        return categoria + nombre + proveedor;
    }
}
