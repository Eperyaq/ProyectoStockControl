package com.es.stockcontrol.dbConnection;

import jakarta.persistence.EntityManager;

public interface IDBConnection {
    EntityManager getEntityManager();
    void closeEntityManager(EntityManager entityManager);
    void closeEntityManagerFactory();
}
