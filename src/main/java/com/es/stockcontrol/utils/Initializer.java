package com.es.stockcontrol.utils;

import com.es.stockcontrol.repository.impl.UserRepository;

/**
 * Clase encargada de inicializar usuarios en la base de datos si no existen.
 * Esta clase se utiliza para gestionar la inserción automática de usuarios al iniciar la aplicación.
 */
public class Initializer {

    public static void usersInyection() {

        UserRepository userRepository = new UserRepository();

        if (userRepository.getUser("Elia") == null) {
            userRepository.insert("Elia", "123");
        }

        if (userRepository.getUser("Sara") == null) {
            userRepository.insert("Sara", "12");
        }

        if (userRepository.getUser("Manu") == null) {
            userRepository.insert("Manu", "1");
        }
    }
}
