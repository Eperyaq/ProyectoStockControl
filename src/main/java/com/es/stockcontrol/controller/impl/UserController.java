package com.es.stockcontrol.controller.impl;

import com.es.stockcontrol.controller.api.UserControllerAPI;
import com.es.stockcontrol.model.entities.RespuestaHTTP;
import com.es.stockcontrol.model.entities.User;
import com.es.stockcontrol.service.impl.UserService;
import com.es.stockcontrol.service.interfaces.IUserService;
import com.es.stockcontrol.utils.Utils;

import static org.hibernate.internal.util.collections.CollectionHelper.listOf;

/**
 * Controlador para gestionar las operaciones relacionadas con usuarios.
 * Implementa la interfaz UserControllerAPI para cumplir con los métodos definidos en la API de usuario.
 */
public class UserController implements UserControllerAPI {

    // Servicio para manejar la lógica de negocio de los usuarios
    IUserService service = new UserService();

    /**
     * Realiza el proceso de inicio de sesión verificando el nombre de usuario y la contraseña proporcionados.
     *
     * @param userInput Nombre de usuario ingresado por el usuario
     * @param passInput Contraseña ingresada por el usuario
     * @return Una instancia de RespuestaHTTP que contiene el estado HTTP, un mensaje y, si el inicio de sesión
     * es exitoso, el usuario correspondiente; de lo contrario, devuelve un mensaje de error.
     */
    @Override
    public RespuestaHTTP<User> login(String userInput, String passInput) {

        if (Utils.verificarLista(listOf(userInput,passInput))){

            User usuarioDentro = service.getUser(userInput);

            if (usuarioDentro != null && passInput.equals(usuarioDentro.getContrasenia())) {
                return new RespuestaHTTP<>(200, "ok", usuarioDentro);
            } else {
                return new RespuestaHTTP<>(404, "Not found", null);
            }

        }

        return new RespuestaHTTP<>(404, "Not found", null);

    }
}
