package com.es.stockcontrol.service.impl;

import com.es.stockcontrol.model.entities.Proveedor;
import com.es.stockcontrol.repository.impl.ProveedorRepository;
import com.es.stockcontrol.service.interfaces.IProveedorService;

import java.util.List;

/**
 * Clase que implementa la lógica de negocio relacionada con los proveedores.
 * Proporciona métodos para gestionar proveedores utilizando un repositorio de proveedores.
 */
public class ProveedorService implements IProveedorService {
    private final ProveedorRepository proveedorRepository;

    /**
     * Constructor que recibe un repositorio de proveedores para manejar las operaciones CRUD.
     * @param proveedorRepository El repositorio de proveedores a utilizar.
     */
    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public void addProveedor(String nombre, String direccion) {
        // TODO
    }

    @Override
    public Proveedor getProveedorById(long id) {
        return proveedorRepository.getProveedorById(id);
    }

    @Override
    public Proveedor updateProveedor(long id, String nuevoNombre, String nuevaDireccion) {
        // TODO
        return null;
    }

    @Override
    public void deleteProveedor(long id) {
        // TODO
    }

    @Override
    public List<Proveedor> getAllProveedores() {
        List<Proveedor> proveedores = proveedorRepository.getAll();
        return (proveedores != null) ? proveedores : List.of();
    }
}
