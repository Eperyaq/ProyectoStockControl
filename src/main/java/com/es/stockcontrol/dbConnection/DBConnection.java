package com.es.stockcontrol.dbConnection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBConnection implements IDBConnection {

    /**
     * Singleton. Instancia única de EntityManagerFactory
     */
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ProyectoStock");

    /**
     * Método para obtener un EntityManager
     */
    @Override
    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    /**
     * Método para cerrar EntityManager
     */
    @Override
    public void closeEntityManager(EntityManager entityManager) {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }

    /**
     * Método para cerrar EntityManagerFactory al finalizar la aplicación
     */
    @Override
    public void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
