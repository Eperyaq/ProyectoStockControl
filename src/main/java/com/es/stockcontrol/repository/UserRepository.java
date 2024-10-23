package com.es.stockcontrol.repository;

import com.es.stockcontrol.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * Repositorio de la entidad User que maneja operaciones de persistencia.
 * Utiliza JPA para interactuar con la base de datos.
 */
public class UserRepository {



    /**
     * Inserta un nuevo usuario en la base de datos.
     *
     * @param nuevoUsuario El objeto User que se va a insertar.
     * @return El objeto User insertado.
     */
    public User insertUser(User nuevoUsuario) {

        // EntityManagerFactory para gestionar las instancias de EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoStock");

        // EntityManager para interactuar con la base de datos
        EntityManager em = emf.createEntityManager();

        // EntityTransaction para gestionar transacciones de base de datos
        EntityTransaction transaccion = em.getTransaction();

        try {
            // Inicia la transacción
            transaccion.begin();

            //Hace un insert en la base de datos
            em.persist(nuevoUsuario);

            // Confirma la transacción
            transaccion.commit();
            return nuevoUsuario;

        } catch (Exception e) {
            // Si hay un error, revierte la transacción
            if (transaccion.isActive()) {
                transaccion.rollback();
            }
            // Cierra el EntityManager
            em.close();
            return null;
        }
        // Retorna el usuario insertado

    }

    public User getUser(String identificador){
        // EntityManagerFactory para gestionar las instancias de EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoStock");

        // EntityManager para interactuar con la base de datos
        EntityManager em = emf.createEntityManager();

        return em.find(User.class, identificador);
    }
}
