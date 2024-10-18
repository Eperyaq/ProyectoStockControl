package com.es.stockcontrol.model;

import jakarta.persistence.OneToMany;

import java.util.List;

public class Proveedor {

    private long id;

    private String nombre;

    private String direccion;


    @OneToMany
    private List<Producto> listaProductos;


}
