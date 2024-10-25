package com.es.stockcontrol.dbConnection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBConnection {

    /**
     * Singleton. Instancia única de EntityManagerFactory
     */
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ProyectoStock");

    /**
     * Método para obtener un EntityManager
     */
    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    /**
     * Método para cerrar EntityManager
     */
    public static void closeEntityManager(EntityManager entityManager) {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }

    /**
     * Método para cerrar EntityManagerFactory al finalizar la aplicación
     */
    public static void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
