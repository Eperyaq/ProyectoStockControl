package com.es.stockcontrol.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad que representa un usuario en el sistema.
 * Mapeada a la tabla 'user' en la base de datos.
 */
@Entity
@Table(name = "user")
public class User {

    /**
     * Identificador único del usuario (nombre de usuario).
     */
    @Id
    private String nombre_usuario;

    /**
     * Contraseña del usuario.
     * No puede ser nula y tiene un máximo de 20 caracteres.
     */
    @Column(name = "contrasenia", nullable = false, length = 20)
    private String contrasenia;

    /**
     * Constructor sin parámetros requerido por JPA.
     */
    public User() {}

    /**
     * Constructor que inicializa el nombre de usuario y la contraseña.
     *
     * @param nombre_usuario nombre de usuario único
     * @param contrasenia    contraseña del usuario
     */
    public User(String nombre_usuario, String contrasenia) {
        this.nombre_usuario = nombre_usuario;
        this.contrasenia = contrasenia;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return el nombre de usuario
     */
    public String getNombre_usuario() {
        return nombre_usuario;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param nombre_usuario el nombre de usuario a establecer
     */
    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return la contraseña del usuario
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contrasenia la contraseña a establecer
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * Representación en cadena de texto del objeto User.
     *
     * @return una cadena de texto que representa al usuario
     */
    @Override
    public String toString() {
        return "User {" +
                "\n\tNombre de Usuario: '" + nombre_usuario + '\'' +
                "\n}";
    }
}
