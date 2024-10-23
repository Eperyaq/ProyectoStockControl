package com.es.stockcontrol.repository.interfaces;


import com.es.stockcontrol.model.entities.Proveedor;

import java.util.List;

/** <p>
 * La interfaz IProveedorRepository define los métodos CRUD para la entidad Proveedor.
 *</p>
 * <p>
 * Proporciona una abstracción para gestionar proveedores en la base de datos, permitiendo que
 * las implementaciones puedan ser intercambiadas fácilmente.
 * </p>
 */
public interface IProveedorRepository {
    void insert(String nombre, String direccion);
    Proveedor getProveedorById(long id);
    Proveedor modify(long id, String nuevoNombre, String nuevaDireccion);
    void delete(long id);
    List<Proveedor> getAll();
}
