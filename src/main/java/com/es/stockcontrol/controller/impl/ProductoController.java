package com.es.stockcontrol.controller.impl;

import com.es.stockcontrol.controller.api.ProductoControllerAPI;
import com.es.stockcontrol.model.entities.Producto;
import com.es.stockcontrol.model.entities.Proveedor;
import com.es.stockcontrol.model.entities.RespuestaHTTP;
import com.es.stockcontrol.service.impl.ProductoService;
import com.es.stockcontrol.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductoController implements ProductoControllerAPI {

    private final ProductoService productoService = new ProductoService();


    @Override
    public RespuestaHTTP<Producto> altaProducto(String categoriaProducto, String nombreProducto, String precioSinIva, String descripcionProducto, String nombreProveedor, String direccionProveedor) {

        try {
            Producto producto = productoService.createProducto(categoriaProducto, nombreProducto, precioSinIva, descripcionProducto, nombreProveedor, direccionProveedor);
            if (producto == null) {
                return new RespuestaHTTP<>(400, "Error al crear el producto", null);
            } else {
                return new RespuestaHTTP<>(200, "Producto dado de alta", producto);
            }
        } catch (Exception e) {
            return new RespuestaHTTP<>(500, "Error interno del servidor " + e.getMessage(), null);
        }
    }

    @Override
    public RespuestaHTTP<Producto> bajaProducto(String id) {
        try {
            if (productoService.deleteProductoById(id)) {
                return new RespuestaHTTP<>(200, "Producto eliminado correctamente", null);

            } else {
                return new RespuestaHTTP<>(400, "Id no valido", null);
            }
        } catch (Exception e) {
            return new RespuestaHTTP<>(500, "Error interno del servidor " + e.getMessage(), null);
        }
    }

    @Override
    public RespuestaHTTP<Producto> modificarNombreProducto(String id, String nuevoNombre) {
        try {
            Producto producto = productoService.updateNombreProducto(id, nuevoNombre);
            if (producto != null) {
                return new RespuestaHTTP<>(200, "Producto modificado correctamente", producto);

            } else {
                return new RespuestaHTTP<>(400, "Error al modificar el producto", null);
            }
        } catch (Exception e) {
            return new RespuestaHTTP<>(500, "Error interno del servidor " + e.getMessage(), null);
        }
    }

    @Override
    public RespuestaHTTP<Producto> modificarStockProducto(String id, String nuevoStock) {
        try {
            Producto producto = productoService.updateStockProducto(id, nuevoStock);
            if (producto != null) {
                return new RespuestaHTTP<>(200, "Producto modificado correctamente", producto);

            } else {
                return new RespuestaHTTP<>(400, "Error al modificar el producto", null);
            }
        } catch (Exception e) {
            return new RespuestaHTTP<>(500, "Error interno del servidor " + e.getMessage(), null);
        }
    }

    @Override
    public RespuestaHTTP<Producto> getProducto(String id) {
        try {
            if (id.isEmpty()) {
                return new RespuestaHTTP<>(400, "ID no valido", null);
            }

            Producto producto = productoService.getProductoById(id);
            if (producto != null) {
                return new RespuestaHTTP<>(200, "Producto obtenido correctamente", producto);

            } else {
                return new RespuestaHTTP<>(404, "Producto no encontrado", null);
            }
        } catch (Exception e) {
            return new RespuestaHTTP<>(500, "Error interno del servidor " + e.getMessage(), null);
        }
    }

    @Override
    public RespuestaHTTP<List<Producto>> getProductosConStock() {
        try {

            List<Producto> productos = productoService.getProductosByStock(true);
            if (productos != null) {
                return new RespuestaHTTP<>(200, "Productos obtenido correctamente", productos);

            } else {
                return new RespuestaHTTP<>(404, "Error al obtener los productos", null);
            }
        } catch (Exception e) {
            return new RespuestaHTTP<>(500, "Error interno del servidor " + e.getMessage(), null);
        }
    }

    @Override
    public RespuestaHTTP<List<Producto>> getProductosSinStock() {
        try {

            List<Producto> productos = productoService.getProductosByStock(false);
            if (productos != null) {
                return new RespuestaHTTP<>(200, "Productos obtenido correctamente", productos);

            } else {
                return new RespuestaHTTP<>(404, "Error al obtener los productos", null);
            }
        } catch (Exception e) {
            return new RespuestaHTTP<>(500, "Error interno del servidor " + e.getMessage(), null);
        }
    }
}
