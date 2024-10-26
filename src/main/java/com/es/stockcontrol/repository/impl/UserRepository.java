package com.es.stockcontrol.repository.impl;

import com.es.stockcontrol.dbConnection.DBConnection;
import com.es.stockcontrol.model.entities.User;
import com.es.stockcontrol.repository.interfaces.IUserRepository;
import jakarta.persistence.*;

/**
 * Implementación del repositorio de la entidad {@link User} para manejar operaciones de persistencia.
 * Utiliza JPA a través de un {@link EntityManager} para interactuar con la base de datos.
 */
public class UserRepository implements IUserRepository {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoStock");
    EntityManager em = emf.createEntityManager();

    /**
     * Busca un usuario en la base de datos utilizando el nombre de usuario proporcionado.
     *
     * @param nombreUsuario el identificador del usuario que se va a buscar.
     * @return el {@link User} encontrado con el nombre de usuario especificado o {@code null} si no se encuentra.
     */
    @Override
    public User getUser(String nombreUsuario) {
        EntityManager database = DBConnection.getEntityManager();

        User usuarioBuscado = null;
        try {
            usuarioBuscado = database.createQuery("SELECT u FROM User u WHERE u.nombre_usuario = :nombreUsuario", User.class)
                    .setParameter("nombreUsuario", nombreUsuario)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No se encontro ningun usuario con el nombre de usuario: " + nombreUsuario);
        }
        return usuarioBuscado;
    }

    public void insert(String usuario, String contrasenia) {
        EntityTransaction transaction = em.getTransaction();
        User user = new User(usuario, contrasenia);
        try {
            transaction.begin();
            em.persist(user);
            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
