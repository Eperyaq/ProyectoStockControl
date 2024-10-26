package com.es.stockcontrol.service.impl;

import com.es.stockcontrol.model.entities.User;
import com.es.stockcontrol.repository.impl.UserRepository;

public class UserService {
    UserRepository repository = new UserRepository();


    public User getUser( String nombre){
        if (nombre.isEmpty() || nombre == null){
            return null;
        }else {
            return repository.getUser(nombre);
        }

    }



}
