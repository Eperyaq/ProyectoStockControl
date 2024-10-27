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

    /**
     * Agrega un nuevo proveedor con los datos proporcionados.
     * @param nombre El nombre del proveedor.
     * @param direccion La dirección del proveedor.
     */
    @Override
    public void addProveedor(String nombre, String direccion) {
        if (nombre != null && !nombre.trim().isEmpty() && direccion != null && !direccion.trim().isEmpty()) {
            proveedorRepository.insert(nombre, direccion);
        }
    }

    /**
     * Obtiene un proveedor por su identificador.
     * @param id El identificador del proveedor.
     * @return El proveedor correspondiente al ID, o null si no se encuentra.
     */
    @Override
    public Proveedor getProveedorById(long id) {
        return proveedorRepository.getProveedorById(id);
    }

    /**
     * Actualiza los datos de un proveedor existente.
     * @param id El identificador del proveedor.
     * @param nuevoNombre El nuevo nombre del proveedor.
     * @param nuevaDireccion La nueva dirección del proveedor.
     * @return El proveedor actualizado, o null si la actualización falla.
     */
    @Override
    public Proveedor updateProveedor(long id, String nuevoNombre, String nuevaDireccion) {
        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty() && nuevaDireccion != null && !nuevaDireccion.trim().isEmpty()) {
            return proveedorRepository.modify(id, nuevoNombre, nuevaDireccion);
        }
        return null;
    }

    /**
     * Elimina un proveedor por su identificador.
     * @param id El identificador del proveedor.
     */
    @Override
    public void deleteProveedor(long id) {
        proveedorRepository.delete(id);
    }

    /**
     * Obtiene una lista de todos los proveedores registrados en la base de datos.
     * @return Una lista con todos los proveedores.
     */
    @Override
    public List<Proveedor> getAllProveedores() {
        List<Proveedor> proveedores = proveedorRepository.getAll();
        return (proveedores != null) ? proveedores : List.of();
    }

    /**
     * Obtiene una lista de proveedores que suministran un producto específico.
     * @param idProducto El identificador del producto.
     * @return Una lista de proveedores que suministran el producto dado.
     */
    @Override
    public List<Proveedor> getProveedoresPorProducto(String idProducto) {
        List<Proveedor> proveedores = proveedorRepository.getProveedoresPorProducto(idProducto);
        return (proveedores != null) ? proveedores : List.of();
    }

}
