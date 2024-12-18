package com.es.stockcontrol.service.impl;

import com.es.stockcontrol.dbConnection.DBConnection;
import com.es.stockcontrol.model.entities.Producto;
import com.es.stockcontrol.model.entities.Proveedor;
import com.es.stockcontrol.repository.impl.ProductoRepository;
import com.es.stockcontrol.repository.impl.ProveedorRepository;
import com.es.stockcontrol.service.interfaces.IProductoService;
import com.es.stockcontrol.utils.Utils;

import java.util.Date;
import java.util.List;

import static org.hibernate.internal.util.collections.CollectionHelper.listOf;

public class ProductoService implements IProductoService {

    private final ProductoRepository productoRepository = new ProductoRepository();
    private final DBConnection dbConnection = new DBConnection();

    @Override
    public Producto createProducto(String categoriaProducto, String nombreProducto, String precioSinIva, String descripcionProducto, String nombreProveedor, String direccionProveedor) {


        if (Utils.verificarLista(listOf(categoriaProducto, nombreProducto, precioSinIva, descripcionProducto, nombreProveedor, direccionProveedor))) {
            ProveedorRepository proveedorRepository = new ProveedorRepository(dbConnection);

            Proveedor proveedor = proveedorRepository.getProveedorByNombre(nombreProveedor);
            if (proveedor == null) {
                proveedor = new Proveedor(nombreProveedor, direccionProveedor);
                proveedorRepository.insert(proveedor);
            }

            Proveedor proveedor1 = proveedorRepository.getProveedorByNombre(nombreProveedor);

            Date date = new Date();
            String id = generarProductoId(categoriaProducto, nombreProducto, nombreProveedor);
            float precioSinIva2 = Float.parseFloat(precioSinIva);
            float precioConIva = (float) (precioSinIva2 * 1.21);
            int stock = 0;

            Producto producto = new Producto(id, categoriaProducto, nombreProducto, descripcionProducto, precioSinIva2, precioConIva, date, stock, proveedor1);

            return productoRepository.createProducto(producto);
        }
        return null;
    }

    @Override
    public Producto getProductoById(String id) {

        if (id == null || id.isEmpty()) {
            return null;
        }

        return productoRepository.getProductoById(id);
    }

    @Override
    public List<Producto> getProductosByStock(boolean stockDisponible) {
        return productoRepository.getProductoByStock(stockDisponible);
    }


    @Override
    public boolean deleteProductoById(String id) {
        if (id == null || id.isEmpty()) {
            return false;
        }
        return productoRepository.deleteProductoById(id);
    }

    @Override
    public Producto updateNombreProducto(String id, String nuevoNombre) {
        if (Utils.verificarLista(listOf(id, nuevoNombre))) {
            return productoRepository.updateNombreProducto(id, nuevoNombre);
        }
        return null;
    }

    @Override
    public Producto updateStockProducto(String id, String nuevoStock) {
        if (Utils.verificarLista(listOf(id, nuevoStock))) {
            try {
                int stock = Integer.parseInt(nuevoStock);
                return productoRepository.updateStockProducto(id, stock);

            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    private String generarProductoId(String categoria, String nombre, String nombreProveedor) {
        String categoria2 = categoria.substring(0, 3);
        String nombre2 = nombre.substring(0, 3);
        String proveedor2 = nombreProveedor.substring(0, 3);

        String id = categoria2 + nombre2 + proveedor2;

        //Testeo
        System.out.println(id);

        return id;
    }
}
