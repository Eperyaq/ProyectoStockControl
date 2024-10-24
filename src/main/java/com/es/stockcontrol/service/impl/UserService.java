package com.es.stockcontrol.service.impl;

import com.es.stockcontrol.model.entities.User;
import com.es.stockcontrol.repository.impl.UserRepository;

public class UserService {
    UserRepository repository = new UserRepository();

    public User insertUser(String nombre, String contrasenia){
        User crearUsuario = new User(nombre, contrasenia);

        if (crearUsuario != null){
            User insertarUser = repository.insertUser(crearUsuario);

            if (insertarUser != null){
                return insertarUser;
            } else {
                return null;
            }

        }else {
            return null;
        }

    }

    public User getUser( String nombre){

        return repository.getUser(nombre);


    }



}
