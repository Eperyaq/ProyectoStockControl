package com.es.stockcontrol.controller.impl;

import com.es.stockcontrol.controller.api.ProveedorControllerAPI;
import com.es.stockcontrol.model.entities.Proveedor;
import com.es.stockcontrol.model.entities.RespuestaHTTP;

import java.util.List;

public class ProveedorController implements ProveedorControllerAPI {
    @Override
    public RespuestaHTTP<List<Proveedor>> getProveedoresProducto(String idProducto) {
        return null;
    }

    @Override
    public RespuestaHTTP<List<Proveedor>> getTodosProveedores() {
        return null;
    }
}
