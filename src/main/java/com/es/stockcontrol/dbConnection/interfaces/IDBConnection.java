package com.es.stockcontrol.dbConnection.interfaces;

import jakarta.persistence.EntityManager;

public interface IDBConnection {
    EntityManager getEntityManager();
    void closeEntityManager(EntityManager entityManager);
    void closeEntityManagerFactory();
}
