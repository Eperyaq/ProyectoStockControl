package com.es.stockcontrol.service.interfaces;

import com.es.stockcontrol.model.entities.Producto;
import java.util.List;

/**
 * Interfaz que define los métodos del servicio para la entidad Producto.
 * Proporciona métodos para la creación, obtención, eliminación y actualización de productos.
 */
public interface IProductoService {

    /**
     * Crea un nuevo producto con los datos proporcionados.
     *
     * @param categoriaProducto La categoría del producto.
     * @param nombreProducto El nombre del producto.
     * @param precioSinIva El precio sin IVA del producto.
     * @param descripcionProducto La descripción del producto.
     * @param nombreProveedor El nombre del proveedor del producto.
     * @param direccionProveedor La dirección del proveedor.
     * @return El producto creado o null si la creación falla.
     */
    Producto createProducto(String categoriaProducto, String nombreProducto, String precioSinIva,
                            String descripcionProducto, String nombreProveedor, String direccionProveedor);

    /**
     * Obtiene un producto por su identificador.
     *
     * @param id El identificador del producto.
     * @return El producto correspondiente al ID, o null si no se encuentra.
     */
    Producto getProductoById(String id);

    /**
     * Obtiene una lista de productos dependiendo de si tienen o no stock disponible.
     *
     * @param stockDisponible true si se desean productos con stock, false si se desean productos sin stock.
     * @return Una lista de productos que cumplen con la condición.
     */
    List<Producto> getProductosByStock(boolean stockDisponible);

    /**
     * Elimina un producto por su identificador.
     *
     * @param id El identificador del producto.
     * @return true si el producto fue eliminado correctamente, false de lo contrario.
     */
    boolean deleteProductoById(String id);

    /**
     * Actualiza el nombre de un producto existente.
     *
     * @param id El identificador del producto.
     * @param nuevoNombre El nuevo nombre del producto.
     * @return El producto actualizado, o null si la actualización falla.
     */
    Producto updateNombreProducto(String id, String nuevoNombre);

    /**
     * Actualiza el stock de un producto existente.
     *
     * @param id El identificador del producto.
     * @param nuevoStock El nuevo valor de stock del producto.
     * @return El producto actualizado, o null si la actualización falla.
     */
    Producto updateStockProducto(String id, String nuevoStock);
}
