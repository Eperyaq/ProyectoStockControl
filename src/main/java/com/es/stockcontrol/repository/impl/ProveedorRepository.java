package com.es.stockcontrol.repository.impl;

import com.es.stockcontrol.dbConnection.DBConnection;
import com.es.stockcontrol.model.entities.Proveedor;
import com.es.stockcontrol.repository.interfaces.IProveedorRepository;
import jakarta.persistence.EntityManager;

import java.util.List;


/**
 * <p>
 * La clase ProveedorRepository proporciona los métodos CRUD para gestionar la entidad Proveedor.
 * Se encarga de realizar operaciones de creación, lectura, actualización y eliminación
 * de proveedores en la base de datos utilizando un EntityManager proporcionado por IDBConnection.
 * </p>
 * <p>
 * Cada método CRUD crea su propio EntityManager para manejar la operación específica,
 * iniciando y confirmando transacciones, y liberando los recursos de la conexión al finalizar.
 * </p>
 * <p>
 * Esta clase utiliza el patrón de repositorio para separar la lógica de acceso a datos de la lógica de negocio.
 * </p>
 */
public class ProveedorRepository implements IProveedorRepository {

    private final DBConnection dbConnection;

    /**
     * Constructor de la clase ProveedorRepository.
     * Recibe un objeto IDBConnection para gestionar las conexiones con la base de datos.
     *
     * @param dbConnection El objeto de conexión que proporciona los EntityManager necesarios.
     */
    public ProveedorRepository(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    /**
     * CREATE
     * Inserta un nuevo proveedor en la base de datos.
     *
     * @param nombre    Nombre del nuevo proveedor
     * @param direccion Dirección del nuevo proveedor
     */
    @Override
    public void insert(String nombre, String direccion) {
        EntityManager em = DBConnection.getEntityManager();
        Proveedor nuevoProveedor = new Proveedor();
        nuevoProveedor.setNombre(nombre);
        nuevoProveedor.setDireccion(direccion);

        try {
            em.getTransaction().begin();
            em.persist(nuevoProveedor);
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }


    /**
     * READ
     * Busca un proveedor por su ID.
     *
     * @param id Identificador del proveedor
     * @return Devuelve el Proveedor que coincida con el ID proporcionado
     */
    @Override
    public Proveedor getProveedorById(long id) {
        EntityManager em = DBConnection.getEntityManager();
        Proveedor proveedor = null;

        try {
            em.getTransaction().begin();
            proveedor = em.find(Proveedor.class, id);
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return proveedor;
    }

    /**
     * UPDATE
     *
     * @param id             Identificador del Proveedor a modificar
     * @param nuevoNombre    El nuevo nombre del Proveedor
     * @param nuevaDireccion La nueva dirección del Proveedor
     * @return Devuleve el Proveedor modificado
     */
    @Override
    public Proveedor modify(long id, String nuevoNombre, String nuevaDireccion) {
        EntityManager em = DBConnection.getEntityManager();
        Proveedor proveedor = null;

        try {
            em.getTransaction().begin();
            proveedor = em.find(Proveedor.class, id);
            if (proveedor != null) {
                proveedor.setNombre(nuevoNombre);
                proveedor.setDireccion(nuevaDireccion);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return proveedor;
    }

    /**
     * DELETE
     * Elimina un proveedor por su ID.
     *
     * @param id Identificador del proveedor a eliminar
     */
    @Override
    public void delete(long id) {
        EntityManager em = DBConnection.getEntityManager();
        Proveedor proveedor;

        try {
            em.getTransaction().begin();
            proveedor = em.find(Proveedor.class, id);
            if (proveedor != null) {
                em.remove(proveedor);
            }
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

    }

    /**
     * Obtiene una lista de todos los proveedores registrados en la base de datos.
     *
     * @return Devuelve una lista con todos los registros de la tabla Proveedores
     */
    @Override
    public List<Proveedor> getAll() {
        EntityManager em = DBConnection.getEntityManager();
        List<Proveedor> proveedores = null;

        try {
            em.getTransaction().begin();
            proveedores = em.createQuery("SELECT proveedor FROM Proveedor proveedor", Proveedor.class).getResultList();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return proveedores;
    }

    /**
     * Obtiene una lista de proveedores que suministran un producto específico.
     * @param idProducto El identificador del producto.
     * @return Lista de proveedores que suministran el producto dado.
     */
    @Override
    public List<Proveedor> getProveedoresPorProducto(String idProducto) {
        EntityManager em = DBConnection.getEntityManager();
        List<Proveedor> proveedores = null;

        try {
            em.getTransaction().begin();
            proveedores = em.createQuery(
                    """
                            SELECT proveedor FROM Proveedor proveedor
                            JOIN proveedor.productos producto
                            WHERE producto.id =: idProducto;
                            """,Proveedor.class)
                    .setParameter("idProducto", idProducto)
                    .getResultList();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return (proveedores != null) ? proveedores : List.of();
    }


}
