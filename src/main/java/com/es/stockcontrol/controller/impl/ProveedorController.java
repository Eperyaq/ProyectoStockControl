package com.es.stockcontrol.controller.impl;

import com.es.stockcontrol.controller.api.ProveedorControllerAPI;
import com.es.stockcontrol.model.entities.Proveedor;
import com.es.stockcontrol.model.entities.RespuestaHTTP;
import com.es.stockcontrol.service.impl.ProveedorService;

import java.util.List;

/**
 * Controlador encargado de manejar las solicitudes relacionadas con la entidad Proveedor.
 */
public class ProveedorController implements ProveedorControllerAPI {
    private final ProveedorService proveedorService;

    /**
     * Constructor que recibe un servicio de proveedores para manejar la lógica de negocio.
     * @param proveedorService El servicio de proveedores a utilizar.
     */
    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @Override
    public RespuestaHTTP<List<Proveedor>> getProveedoresProducto(String idProducto) {

        return new RespuestaHTTP<>();
    }

    @Override
    public RespuestaHTTP<List<Proveedor>> getTodosProveedores() {
        return null;
    }
}
