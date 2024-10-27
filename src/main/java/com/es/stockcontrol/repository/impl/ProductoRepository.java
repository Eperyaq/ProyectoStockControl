package com.es.stockcontrol.repository.impl;

import com.es.stockcontrol.model.entities.Producto;
import com.es.stockcontrol.repository.interfaces.IProductoRepository;
import jakarta.persistence.*;

import java.util.List;

public class ProductoRepository implements IProductoRepository {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoStock");
    EntityManager em = emf.createEntityManager();

    @Override
    public Producto createProducto(Producto producto) {

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(producto);
            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return producto;
    }

    @Override
    public boolean deleteProductoById(String id) {
        Producto producto = em.find(Producto.class, id);

        if (producto != null) {

            EntityTransaction transaction = em.getTransaction();

            try {
                transaction.begin();
                em.remove(producto);
                transaction.commit();

                return true;

            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();

                return false;
            }
        }
        return false;
    }

    @Override
    public Producto updateNombreProducto(String id, String nuevoNombre) {

        Producto producto = em.find(Producto.class, id);

        if (producto != null) {

            EntityTransaction transaction = em.getTransaction();

            try {
                transaction.begin();
                producto.setNombre(nuevoNombre);
                em.merge(producto);
                transaction.commit();

            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
        
        return producto;
    }

    @Override
    public Producto updateStockProducto(String id, int nuevoStock) {

        Producto producto = em.find(Producto.class, id);

        if (producto != null) {

            EntityTransaction transaction = em.getTransaction();

            try {
                transaction.begin();
                producto.setStock(nuevoStock);
                em.merge(producto);
                transaction.commit();

            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }

        return producto;
    }


    @Override
    public Producto getProductoById(String id) {
        return em.find(Producto.class, id);
    }

    @Override
    public List<Producto> getProductoByStock(Boolean stock) {

        String jpql;

        if (stock) {
            jpql = "SELECT p FROM Producto p WHERE p.stock > 0";
        } else {
            jpql = "SELECT p FROM Producto p WHERE p.stock < 0";
        }

        TypedQuery<Producto> query = em.createQuery(jpql, Producto.class);

        return query.getResultList();
    }
}
