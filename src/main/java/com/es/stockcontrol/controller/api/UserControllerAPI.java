package com.es.stockcontrol.controller.api;

import com.es.stockcontrol.model.entities.RespuestaHTTP;
import com.es.stockcontrol.model.entities.User;

public interface UserControllerAPI {

    /**
     * Método para realizar un login en la aplicacion.
     *
     * @param userInput String. Parametro con el usuario introducido por teclado
     * @param passInput String. Parametro con la pass introducida por teclado
     * @return RespuestaHTTP<User>
     */
    RespuestaHTTP<User> login(String userInput, String passInput);
}
