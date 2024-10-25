package com.es.stockcontrol.controller.impl;

import com.es.stockcontrol.controller.api.ProveedorControllerAPI;
import com.es.stockcontrol.model.entities.Proveedor;
import com.es.stockcontrol.model.entities.RespuestaHTTP;
import com.es.stockcontrol.service.impl.ProductoService;
import com.es.stockcontrol.service.impl.ProveedorService;

import java.util.List;

/**
 * Controlador encargado de manejar las solicitudes relacionadas con la entidad Proveedor.
 */
public class ProveedorController implements ProveedorControllerAPI {
    private final ProveedorService proveedorService;
    private final ProductoService productoService;

    /**
     * Constructor que recibe servicios de proveedores y productos para manejar la lógica de negocio.
     * @param proveedorService El servicio de proveedores a utilizar.
     * @param productoService  El servicio de productos a utilizar.
     */
    public ProveedorController(ProveedorService proveedorService, ProductoService productoService) {
        this.proveedorService = proveedorService;
        this.productoService = productoService;
    }

    @Override
    public RespuestaHTTP<List<Proveedor>> getProveedoresProducto(String idProducto) {
        List<Proveedor> proveedores = proveedorService.
        try {
            if (idProducto == null || idProducto.isEmpty()) {
                return new RespuestaHTTP<>(400, "ID de producto inválido.", null);
            }
            if (productoService.getProductoById(idProducto) == null) {
                return new RespuestaHTTP<>(404, "Producto no encontrado", null);
            }
        } catch (Exception e) {
            return new RespuestaHTTP<>(500, "Error interno del servidor " + e.getMessage(), null);
        }

    }

    @Override
    public RespuestaHTTP<List<Proveedor>> getTodosProveedores() {
        List<Proveedor> proveedores = proveedorService.getAllProveedores();
        try {
            if (proveedores)
        } catch (Exception e) {
            return new RespuestaHTTP<>(500, "Error interno del servidor " + e.getMessage(), null);
        }
        return null;
    }
}
