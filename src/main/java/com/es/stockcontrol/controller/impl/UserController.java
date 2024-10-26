package com.es.stockcontrol.controller.impl;

import com.es.stockcontrol.controller.api.UserControllerAPI;
import com.es.stockcontrol.model.entities.RespuestaHTTP;
import com.es.stockcontrol.model.entities.User;
import com.es.stockcontrol.service.impl.UserService;

public class UserController implements UserControllerAPI {

UserService service = new UserService();

    @Override
    public RespuestaHTTP<User> login(String userInput, String passInput) {

        if (userInput.isEmpty() || userInput == null && passInput.isEmpty() || passInput == null){

            return new RespuestaHTTP(404, "Not found", null);
        }else {
            User usuarioDentro = service.getUser(userInput);

            if (usuarioDentro != null && passInput.equals(usuarioDentro.getContrasenia())) {
                return new RespuestaHTTP<>(200, "ok", usuarioDentro);
            } else {
                return new RespuestaHTTP<>(404, "Not found", null);
            }

        }

    }
}
