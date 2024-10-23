package com.es.stockcontrol.controller.impl;


import com.es.stockcontrol.controller.api.UserControllerAPI;
import com.es.stockcontrol.model.entities.RespuestaHTTP;
import com.es.stockcontrol.model.entities.User;

public class UserController implements UserControllerAPI {


    @Override
    public RespuestaHTTP<User> login(String userInput, String passInput) {
        return null;
    }
}
