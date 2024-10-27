package com.es.stockcontrol.repository.interfaces;


import com.es.stockcontrol.model.entities.Proveedor;

import java.util.List;

/**
 * <p>
 * La interfaz IProveedorRepository define los métodos CRUD para la entidad Proveedor.
 * Proporciona una abstracción para gestionar proveedores en la base de datos, permitiendo que
 * las implementaciones puedan ser intercambiadas fácilmente.
 * </p>
 */
public interface IProveedorRepository {
    /**
     * Inserta un nuevo proveedor en la base de datos.
     *
     * @param nombre    El nombre del proveedor a insertar.
     * @param direccion La dirección del proveedor.
     */
    void insert(String nombre, String direccion);

    /**
     * Obtiene un proveedor por su ID.
     *
     * @param id El identificador del proveedor.
     * @return El proveedor que coincide con el ID proporcionado, o null si no se encuentra.
     */
    Proveedor getProveedorById(long id);
    /**
     * Modifica los datos de un proveedor existente.
     *
     * @param id            El identificador del proveedor a modificar.
     * @param nuevoNombre   El nuevo nombre del proveedor.
     * @param nuevaDireccion La nueva dirección del proveedor.
     * @return El proveedor modificado.
     */
    Proveedor modify(long id, String nuevoNombre, String nuevaDireccion);

    /**
     * Elimina un proveedor de la base de datos por su ID.
     *
     * @param id El identificador del proveedor a eliminar.
     */
    void delete(long id);

    /**
     * Obtiene una lista de todos los proveedores registrados en la base de datos.
     *
     * @return Una lista con todos los proveedores.
     */
    List<Proveedor> getAll();

    /**
     * Obtiene una lista de proveedores que suministran un producto específico.
     *
     * @param idProducto El identificador del producto.
     * @return Una lista de proveedores que suministran el producto dado.
     */
    List<Proveedor> getProveedoresPorProducto(String idProducto);

    /**
     * Obtiene un proveedor por su nombre.
     *
     * @param nombre El nombre del proveedor a buscar.
     * @return El proveedor que coincide con el nombre proporcionado, o null si no se encuentra.
     */
    Proveedor getProveedorByNombre(String nombre);
}
