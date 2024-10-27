package com.es.stockcontrol.controller.impl;

import com.es.stockcontrol.controller.api.ProveedorControllerAPI;
import com.es.stockcontrol.model.entities.Proveedor;
import com.es.stockcontrol.model.entities.RespuestaHTTP;
import com.es.stockcontrol.service.impl.ProductoService;
import com.es.stockcontrol.service.impl.ProveedorService;

import java.util.List;

/**
 * Controlador encargado de manejar las solicitudes relacionadas con la entidad Proveedor.
 */
public class ProveedorController implements ProveedorControllerAPI {

    private final ProveedorService proveedorService;
    private final ProductoService productoService;

    /**
     * Constructor que recibe servicios de proveedores y productos para manejar la lógica de negocio.
     * @param proveedorService El servicio de proveedores a utilizar.
     * @param productoService  El servicio de productos a utilizar.
     */
    public ProveedorController(ProveedorService proveedorService, ProductoService productoService) {
        this.proveedorService = proveedorService;
        this.productoService = productoService;
    }

    /**
     * Agrega un nuevo proveedor a la base de datos.
     * @param nombre El nombre del proveedor.
     * @param direccion La dirección del proveedor.
     * @return Un objeto {@link RespuestaHTTP} con:
     * <ul>
     *     <li>Código 200 si se ha añadido correctamente.</li>
     *     <li>Código 400 si los datos proporcionados son inválidos.</li>
     *     <li>Código 500 si ocurre un error interno en el servidor.</li>
     * </ul>
     */
    public RespuestaHTTP<Void> addProveedor(String nombre, String direccion) {
        try {
            if (nombre == null || nombre.trim().isEmpty() || direccion == null || direccion.trim().isEmpty()) {
                return new RespuestaHTTP<>(400, "Nombre o dirección no válidos.", null);
            }
            proveedorService.addProveedor(nombre, direccion);
            return new RespuestaHTTP<>(200, "Proveedor añadido correctamente.", null);
        } catch (Exception e) {
            return new RespuestaHTTP<>(500, "Error interno del servidor " + e.getMessage(), null);
        }
    }

    /**
     * Actualiza un proveedor existente en la base de datos.
     * @param id El identificador del proveedor.
     * @param nuevoNombre El nuevo nombre del proveedor.
     * @param nuevaDireccion La nueva dirección del proveedor.
     * @return Un objeto {@link RespuestaHTTP} con:
     * <ul>
     *     <li>Código 200 si se ha actualizado correctamente.</li>
     *     <li>Código 400 si los datos proporcionados son inválidos.</li>
     *     <li>Código 404 si el proveedor no se encuentra.</li>
     *     <li>Código 500 si ocurre un error interno en el servidor.</li>
     * </ul>
     */
    public RespuestaHTTP<Proveedor> updateProveedor(long id, String nuevoNombre, String nuevaDireccion) {
        try {
            if (nuevoNombre == null || nuevoNombre.trim().isEmpty() || nuevaDireccion == null || nuevaDireccion.trim().isEmpty()) {
                return new RespuestaHTTP<>(400, "Nombre o dirección no válidos.", null);
            }
            Proveedor proveedorActualizado = proveedorService.updateProveedor(id, nuevoNombre, nuevaDireccion);
            if (proveedorActualizado == null) {
                return new RespuestaHTTP<>(404, "Proveedor no encontrado.", null);
            }
            return new RespuestaHTTP<>(200, "Proveedor actualizado correctamente.", proveedorActualizado);
        } catch (Exception e) {
            return new RespuestaHTTP<>(500, "Error interno del servidor " + e.getMessage(), null);
        }
    }

    /**
     * Elimina un proveedor de la base de datos.
     * @param id El identificador del proveedor.
     * @return Un objeto {@link RespuestaHTTP} con:
     * <ul>
     *     <li>Código 200 si se ha eliminado correctamente.</li>
     *     <li>Código 404 si el proveedor no se encuentra.</li>
     *     <li>Código 500 si ocurre un error interno en el servidor.</li>
     * </ul>
     */
    public RespuestaHTTP<Void> deleteProveedor(long id) {
        try {
            Proveedor proveedor = proveedorService.getProveedorById(id);
            if (proveedor == null) {
                return new RespuestaHTTP<>(404, "Proveedor no encontrado.", null);
            }
            proveedorService.deleteProveedor(id);
            return new RespuestaHTTP<>(200, "Proveedor eliminado correctamente.", null);
        } catch (Exception e) {
            return new RespuestaHTTP<>(500, "Error interno del servidor " + e.getMessage(), null);
        }
    }

    /**
     * Obtiene una lista de proveedores que suministran un producto específico.
     * @param idProducto El identificador del producto a buscar.
     * @return Un objeto {@link RespuestaHTTP} que contiene:
     * <ul>
     *     <li>Código 200 si se encontraron proveedores, con la lista de proveedores.</li>
     *     <li>Código 400 si el ID del producto proporcionado es inválido.</li>
     *     <li>Código 404 si no se encuentra el producto o no hay proveedores relacionados.</li>
     *     <li>Código 500 si ocurre un error interno en el servidor.</li>
     * </ul>
     */
    @Override
    public RespuestaHTTP<List<Proveedor>> getProveedoresProducto(String idProducto) {

        try {
            if (idProducto == null || idProducto.isEmpty()) {
                return new RespuestaHTTP<>(400, "ID de producto inválido.", null);
            }
            if (productoService.getProductoById(idProducto) == null) {
                return new RespuestaHTTP<>(404, "Producto no encontrado", null);
            }
            List<Proveedor> proveedores = proveedorService.getProveedoresPorProducto(idProducto);
            if (proveedores != null && !proveedores.isEmpty()) {
                return new RespuestaHTTP<>(200, "Proveedores encontrados", proveedores);
            } else {
                return new RespuestaHTTP<>(404, "No se encontraron proveedores del producto", null);
            }
        } catch (Exception e) {
            return new RespuestaHTTP<>(500, "Error interno del servidor " + e.getMessage(), null);
        }
    }

    /**
     * Obtiene una lista de todos los proveedores registrados en la base de datos.
     * @return Un objeto {@link RespuestaHTTP} que contiene:
     * <ul>
     *     <li>Código 200 si se encontraron proveedores, con la lista de proveedores.</li>
     *     <li>Código 404 si no se encuentran proveedores registrados.</li>
     *     <li>Código 500 si ocurre un error interno en el servidor.</li>
     * </ul>
     */
    @Override
    public RespuestaHTTP<List<Proveedor>> getTodosProveedores() {

        try {
            List<Proveedor> proveedores = proveedorService.getAllProveedores();
            if (proveedores != null && !proveedores.isEmpty()) {
                return new RespuestaHTTP<>(200, "Proveedores encontrados", proveedores);
            } else {
                return new RespuestaHTTP<>(404, "Proveedores no encontrados", null);
            }
        } catch (Exception e) {
            return new RespuestaHTTP<>(500, "Error interno del servidor " + e.getMessage(), null);
        }
    }
}
