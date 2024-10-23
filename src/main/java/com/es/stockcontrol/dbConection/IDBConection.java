package com.es.stockcontrol.dbConection;

import jakarta.persistence.EntityManager;

public interface IDBConection {
    EntityManager getEntityManager();
    void closeEntityManager(EntityManager entityManager);
    void closeEntityManagerFactory();
}
