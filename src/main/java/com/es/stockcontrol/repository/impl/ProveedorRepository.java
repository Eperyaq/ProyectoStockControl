package com.es.stockcontrol.repository.impl;

import com.es.stockcontrol.dbConnection.DBConnection;
import com.es.stockcontrol.model.entities.Proveedor;
import com.es.stockcontrol.repository.interfaces.IProveedorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

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

        try {
            Proveedor nuevoProveedor = new Proveedor();
            nuevoProveedor.setNombre(nombre);
            nuevoProveedor.setDireccion(direccion);

            em.getTransaction().begin();
            em.persist(nuevoProveedor);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en la creación de nuevo proveedor: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void insert(Proveedor proveedor) {

        EntityManager em = DBConnection.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(proveedor);
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
     * READ: Busca un proveedor por su ID.
     *
     * @param id Identificador del proveedor
     * @return Devuelve el Proveedor que coincida con el ID proporcionado
     */
    @Override
    public Proveedor getProveedorById(long id) {
        EntityManager em = DBConnection.getEntityManager();
        try {
            return em.find(Proveedor.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * UPDATE
     * Actualiza los datos de un proveedor por su ID.
     *
     * @param id             Identificador del Proveedor a modificar
     * @param nuevoNombre    El nuevo nombre del Proveedor
     * @param nuevaDireccion La nueva dirección del Proveedor
     * @return Devuleve el Proveedor modificado
     */
    @Override
    public Proveedor modify(long id, String nuevoNombre, String nuevaDireccion) {
        EntityManager em = DBConnection.getEntityManager();

        try {
            Proveedor proveedor = em.find(Proveedor.class, id);
            if (proveedor != null) {
                em.getTransaction().begin();
                proveedor.setNombre(nuevoNombre);
                proveedor.setDireccion(nuevaDireccion);
                em.getTransaction().commit();
                return proveedor;
            }
            return null;
        } catch (NoResultException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error al modificar el proveedor: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
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

        try {
            Proveedor proveedor = em.find(Proveedor.class, id);
            if (proveedor != null) {
                em.getTransaction().begin();
                em.remove(proveedor);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error al eliminar el proveedor: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * READ ALL: Obtiene una lista de todos los proveedores registrados en la base de datos.
     *
     * @return Devuelve una lista con todos los registros de la tabla Proveedores
     */
    @Override
    public List<Proveedor> getAll() {
        EntityManager em = DBConnection.getEntityManager();
        try {
            return em.createQuery("SELECT proveedor FROM Proveedor proveedor", Proveedor.class).getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Obtiene una lista de proveedores que suministran un producto específico.
     *
     * @param idProducto El identificador del producto.
     * @return Lista de proveedores que suministran el producto dado.
     */
    @Override
    public List<Proveedor> getProveedoresPorProducto(String idProducto) {
        EntityManager em = DBConnection.getEntityManager();

        try {
            em.getTransaction().begin();
            return em.createQuery(
                            """
                                    SELECT proveedor FROM Proveedor proveedor
                                    JOIN proveedor.productos producto
                                    WHERE producto.id =: idProducto;
                                    """, Proveedor.class)
                    .setParameter("idProducto", idProducto)
                    .getResultList();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error al obtener proveedores por producto: " + e.getMessage());
            return List.of();
        } finally {
            em.close();
        }
    }

    /**
     * Obtiene un proveedor por su nombre.
     * Este método busca en la lista de todos los proveedores aquel que tenga
     * un nombre coincidente (ignorando mayúsculas y minúsculas) con el nombre proporcionado.
     *
     * @param nombre El nombre del proveedor que se desea buscar.
     * @return El objeto Proveedor que coincide con el nombre proporcionado, o null si no se encuentra ninguno.
     */
    @Override
    public Proveedor getProveedorByNombre(String nombre) {

        List<Proveedor> proveedors = getAll();

        return proveedors.stream()
                .filter(proveedor -> proveedor.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

}
