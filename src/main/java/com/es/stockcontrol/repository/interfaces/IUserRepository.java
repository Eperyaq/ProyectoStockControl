package com.es.stockcontrol.repository.interfaces;

import com.es.stockcontrol.model.entities.User;

/**
 * Interfaz de repositorio para la entidad {@link User}.
 * Define los métodos necesarios para realizar operaciones de persistencia sobre la entidad {@link User}.
 */
public interface IUserRepository {

    /**
     * Busca un usuario en la base de datos utilizando el nombre de usuario proporcionado.
     *
     * @param nombreUsuario el identificador del usuario que se va a buscar.
     * @return el {@link User} encontrado con el nombre de usuario especificado o {@code null} si no se encuentra.
     */
    User getUser(String nombreUsuario);

}
