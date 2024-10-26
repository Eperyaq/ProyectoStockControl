package com.es.stockcontrol.model.entities;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "proovedor")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre_proveedor", unique = true, length = 50, nullable = false)
    private String nombre;


    @Column(name = "direcion_proveedor", nullable = false)
    private String direccion;


    @OneToMany(mappedBy = "proveedor", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<Producto> listaProductos;


    public Proveedor() {
    }

    public Proveedor(String nombre, String direccion, List<Producto> listaProductos) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.listaProductos = listaProductos;
    }

    public Proveedor(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @Override
    public String toString() {
        return """
                Proveedor ->
                Id: %d
                Nombre: '%s'
                Dirección: '%s'
                """.formatted(id,nombre,direccion);
    }
}
