package com.es.stockcontrol.service.interfaces;

import com.es.stockcontrol.model.entities.User;

/**
 * Interfaz de servicio para la entidad {@link User}.
 * Define los métodos necesarios para realizar operaciones de negocio sobre la entidad {@link User}.
 */
public interface IUserService {

    /**
     * Obtiene un usuario utilizando el nombre de usuario proporcionado.
     *
     * @param nombre el nombre de usuario que se va a buscar.
     * @return el {@link User} encontrado con el nombre especificado o {@code null} si no se encuentra.
     */
    User getUser(String nombre);

}
