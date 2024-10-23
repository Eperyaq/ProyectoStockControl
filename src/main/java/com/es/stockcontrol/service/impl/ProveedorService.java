package com.es.stockcontrol.service.impl;

import com.es.stockcontrol.model.entities.Proveedor;
import com.es.stockcontrol.service.interfaces.IProveedorService;

import java.util.List;

public class ProveedorService implements IProveedorService {
    @Override
    public void addProveedor(String nombre, String direccion) {

    }

    @Override
    public Proveedor getProveedorById(long id) {
        return null;
    }

    @Override
    public Proveedor updateProveedor(long id, String nuevoNombre, String nuevaDireccion) {
        return null;
    }

    @Override
    public void deleteProveedor(long id) {

    }

    @Override
    public List<Proveedor> getAllProveedores() {
        return List.of();
    }
}
