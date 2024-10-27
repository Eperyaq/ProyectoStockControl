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

    /**
     * Agrega un nuevo proveedor con los datos proporcionados.
     *
     * @param nombre    El nombre del proveedor.
     * @param direccion La dirección del proveedor.
     */
    void addProveedor(String nombre, String direccion);

    /**
     * Obtiene un proveedor por su identificador.
     *
     * @param id El identificador del proveedor.
     * @return El proveedor correspondiente al ID, o null si no se encuentra.
     */
    Proveedor getProveedorById(long id);

    /**
     * Actualiza los datos de un proveedor existente.
     *
     * @param id            El identificador del proveedor a actualizar.
     * @param nuevoNombre   El nuevo nombre del proveedor.
     * @param nuevaDireccion La nueva dirección del proveedor.
     * @return El proveedor actualizado, o null si la actualización falla.
     */
    Proveedor updateProveedor(long id, String nuevoNombre, String nuevaDireccion);

    /**
     * Elimina un proveedor por su identificador.
     *
     * @param id El identificador del proveedor.
     */
    void deleteProveedor(long id);

    /**
     * Obtiene una lista de todos los proveedores registrados en la base de datos.
     *
     * @return Una lista con todos los proveedores.
     */
    List<Proveedor> getAllProveedores();

    /**
     * Obtiene una lista de proveedores que suministran un producto específico.
     *
     * @param idProducto El identificador del producto.
     * @return Una lista de proveedores que suministran el producto dado.
     */
    List<Proveedor> getProveedoresPorProducto(String idProducto);
}
