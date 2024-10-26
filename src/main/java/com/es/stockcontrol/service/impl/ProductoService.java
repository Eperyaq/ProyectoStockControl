package com.es.stockcontrol.service.impl;

import com.es.stockcontrol.model.entities.Producto;
import com.es.stockcontrol.model.entities.Proveedor;
import com.es.stockcontrol.repository.impl.ProductoRepository;
import com.es.stockcontrol.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hibernate.internal.util.collections.CollectionHelper.listOf;

public class ProductoService {

    private final ProductoRepository productoRepository = new ProductoRepository();


    public Producto createProducto(String categoriaProducto, String nombreProducto, String precioSinIva, String descripcionProducto, String nombreProveedor, String direccionProveedor) {


        if (Utils.verificarLista(listOf(categoriaProducto, nombreProducto, precioSinIva, descripcionProducto, nombreProducto, direccionProveedor))) {
            Proveedor proveedor = new Proveedor(nombreProveedor, direccionProveedor);
            Date date = new Date();
            String id = generarProductoId(categoriaProducto, nombreProducto, proveedor);
            float precioSinIva2 = Float.parseFloat(precioSinIva);
            float precioConIva = (float) (precioSinIva2 * 1.21);
            int stock = 0;

            Producto producto = new Producto(id, categoriaProducto, nombreProducto, descripcionProducto, precioSinIva2, precioConIva, date, stock, proveedor);

            return productoRepository.createProducto(producto);
        }
        return null;
    }


    public Producto getProductoById(String id) {

        if (id == null || id.isEmpty()) {
            return null;
        }

        return productoRepository.getProductoById(id);
    }


    public List<Producto> getProductosByStock(boolean stockDisponible) {
        return productoRepository.getProductoByStock(stockDisponible);
    }



    public boolean deleteProductoById(String id) {
        if (id == null || id.isEmpty()) {
            return false;
        }
        return productoRepository.deleteProductoById(id);
    }


    public Producto updateNombreProducto(String id, String nuevoNombre) {
        if (Utils.verificarLista(listOf(id, nuevoNombre))) {
            return productoRepository.updateNombreProducto(id, nuevoNombre);
        }
        return null;
    }


    public Producto updateStockProducto(String id, String nuevoStock) {
        if (Utils.verificarLista(listOf(id, nuevoStock))) {
            try {
                int stock = Integer.parseInt(nuevoStock);
                return productoRepository.updateStockProducto(id, stock);

            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }


    private String generarProductoId(String categoria, String nombre, Proveedor proveedor) {
        String categoria2 = categoria.substring(0, 3);
        String nombre2 = nombre.substring(0, 3);
        String proveedor2 = proveedor.getNombre().substring(0, 3);

        return categoria2 + nombre2 + proveedor2;
    }
}
