package com.es.stockcontrol.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

public class Producto {

    private String id;


    private String categoria;


    private String nombre;


    private String descripcion;

    private float precio_sin_iva;

    private float precio_con_iva;

    private Date fechaAlta;

    private int stock;

    @ManyToOne
    @JoinColumn(name = "proveedor")
    private Proveedor proveedor;
}
