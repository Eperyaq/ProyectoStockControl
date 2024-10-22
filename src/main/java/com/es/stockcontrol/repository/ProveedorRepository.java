package com.es.stockcontrol.repository;

import com.es.stockcontrol.model.Proveedor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ProveedorRepository {

    // Gestión de conexiones
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoStock");
    EntityManager em = emf.createEntityManager();

    // CRUD

    /**
     * @param nombre    Nombre del nuevo proveedor
     * @param direccion Dirección del nuevo proveedor
     */
    private void insert(String nombre, String direccion) {

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
                emf.close();
            }
        }
    }


    /**
     * @param id Identificador del proveedor
     * @return Devuelve el Proveedor que coincida con id
     */
    private Proveedor getProveedorById(long id) {
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
                emf.close();
            }
        }
        return proveedor;
    }

    // modifyProveedor

    private Proveedor modify(long id, String nuevoNombre, String nuevaDireccion) {
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
                emf.close();
            }
        }
        return proveedor;
    }

    /**
     * @param id Identificador del proveedor
     */
    private void delete(long id) {
        Proveedor proveedor = null;

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
                emf.close();
            }
        }

    }


}
