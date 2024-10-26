package com.es.stockcontrol.service.impl;

import com.es.stockcontrol.model.entities.User;
import com.es.stockcontrol.repository.impl.UserRepository;
import com.es.stockcontrol.repository.interfaces.IUserRepository;
import com.es.stockcontrol.service.interfaces.IUserService;
import com.es.stockcontrol.utils.Utils;

import static org.hibernate.internal.util.collections.CollectionHelper.listOf;

/**
 * Implementación del servicio de usuarios.
 * Esta clase proporciona métodos para manejar operaciones relacionadas con los usuarios.
 */
public class UserService implements IUserService {

    private final IUserRepository repository = new UserRepository();

    /**
     * Obtiene un usuario basado en el nombre proporcionado.
     *
     * @param nombre el nombre del usuario a buscar. No debe ser nulo o vacío.
     * @return el usuario correspondiente al nombre proporcionado, o null si no se encuentra ningún usuario
     *         o si el nombre no es válido.
     */
    @Override
    public User getUser(String nombre) {

        if (Utils.verificarLista(listOf(nombre))) {
            return repository.getUser(nombre);
        }

        return null;
    }
}
