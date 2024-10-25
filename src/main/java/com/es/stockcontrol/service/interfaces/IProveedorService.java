package com.es.stockcontrol.service.interfaces;


import com.es.stockcontrol.model.entities.Proveedor;

import java.util.List;

/**
 * <p>
 * La interfaz IProveedorService define los métodos de negocio relacionados con la entidad Proveedor.
 * </p>
 * <p>
 * Proporciona una abstracción para la gestión de proveedores en la base de datos,
 * permitiendo que las implementaciones puedan ser intercambiadas fácilmente.
 * </p>
 */
public interface IProveedorService {
    void addProveedor(String nombre, String direccion);
    Proveedor getProveedorById(long id);
    Proveedor updateProveedor(long id, String nuevoNombre, String nuevaDireccion);
    void deleteProveedor(long id);
    List<Proveedor> getAllProveedores();
    List<Proveedor> getProveedoresPorProducto(String idProducto);
}
