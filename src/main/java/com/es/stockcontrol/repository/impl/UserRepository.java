package com.es.stockcontrol.repository.impl;

import com.es.stockcontrol.dbConnection.DBConnection;
import com.es.stockcontrol.model.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

/**
 * Repositorio de la entidad User que maneja operaciones de persistencia.
 * Utiliza JPA para interactuar con la base de datos.
 */
public class UserRepository {
    /**
     * Funcion que va a realizar una busqueda en la base de datos para encontrar un usuario basandose en el nombre
     *
     * @param nombreUsuario Identificador del usuario por el que se va a buscar
     * @return un User con el nombre de usuario pasado por parametros
     */
    public User getUser(String nombreUsuario){
        EntityManager database = DBConnection.getEntityManager();

        User usuarioBuscado = null;
// WHERE nombre_usuario = 'Elia'
        try {
            usuarioBuscado = database.createQuery("SELECT u FROM User u WHERE u.nombre_usuario = :nombreUsuario", User.class)
                    .setParameter("nombreUsuario", nombreUsuario)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No se encontró ningún usuario con el nombre de usuario: " + nombreUsuario);
        }
        return usuarioBuscado;
    }
}
