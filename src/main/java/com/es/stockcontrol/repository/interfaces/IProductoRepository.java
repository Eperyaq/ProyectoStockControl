package com.es.stockcontrol.repository.interfaces;

import com.es.stockcontrol.model.entities.Producto;
import java.util.List;

public interface IProductoRepository {

    /**
     * Crea un nuevo producto en la base de datos.
     *
     * @param producto El producto a crear.
     * @return El producto creado.
     */
    Producto createProducto(Producto producto);

    /**
     * Elimina un producto de la base de datos por su identificador.
     *
     * @param id El identificador del producto a eliminar.
     * @return true si el producto fue eliminado correctamente, false de lo contrario.
     */
    boolean deleteProductoById(String id);

    /**
     * Actualiza el nombre de un producto existente en la base de datos.
     *
     * @param id El identificador del producto a actualizar.
     * @param nuevoNombre El nuevo nombre del producto.
     * @return El producto actualizado.
     */
    Producto updateNombreProducto(String id, String nuevoNombre);

    /**
     * Actualiza el stock de un producto existente en la base de datos.
     *
     * @param id El identificador del producto a actualizar.
     * @param nuevoStock El nuevo valor de stock del producto.
     * @return El producto actualizado.
     */
    Producto updateStockProducto(String id, int nuevoStock);

    /**
     * Obtiene un producto por su identificador.
     *
     * @param id El identificador del producto.
     * @return El producto encontrado o null si no se encontró.
     */
    Producto getProductoById(String id);

    /**
     * Obtiene una lista de productos dependiendo de si tienen o no stock.
     *
     * @param stock true si se quieren productos con stock mayor a 0, false si se quieren productos con stock menor a 0.
     * @return Una lista de productos que cumplen con la condición.
     */
    List<Producto> getProductoByStock(Boolean stock);
}
