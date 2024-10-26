package com.es.stockcontrol.model.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    private String id;

    @Column(name = "categoria_producto", length = 10, nullable = false)
    private String categoria;

    @Column(name = "nombre_producto", length = 50, nullable = false)
    private String nombre;

    @Column(name = "descripcion_producto")
    private String descripcion;

    @Column(name = "precio_sin_iva_producto", nullable = false)
    private float precio_sin_iva;

    @Column(name = "precio_con_iva_producto", nullable = false)
    private float precio_con_iva;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "fecha_alta_producto",nullable = false)
    private Date fechaAlta;


    @Column(name = "stock_producto")
    private int stock;

    @ManyToOne
    @JoinColumn(name = "proveedor")
    private Proveedor proveedor;


    public Producto() {}

    //Maybe quitar el id del constructor
    public Producto(String id, String categoria, String nombre, String descripcion, float precio_sin_iva, float precio_con_iva, Date fechaAlta, int stock, Proveedor proveedor) {
        this.id = id;
        this.categoria = categoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio_sin_iva = precio_sin_iva;
        this.precio_con_iva = precio_con_iva;
        this.fechaAlta = fechaAlta;
        this.stock = stock;
        this.proveedor = proveedor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio_sin_iva() {
        return precio_sin_iva;
    }

    public void setPrecio_sin_iva(float precio_sin_iva) {
        this.precio_sin_iva = precio_sin_iva;
    }

    public float getPrecio_con_iva() {
        return precio_con_iva;
    }

    public void setPrecio_con_iva(float precio_con_iva) {
        this.precio_con_iva = precio_con_iva;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id='" + id + '\'' +
                ", categoria='" + categoria + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio_sin_iva=" + precio_sin_iva +
                ", precio_con_iva=" + precio_con_iva +
                ", fechaAlta=" + fechaAlta +
                ", stock=" + stock +
                ", proveedor=" + proveedor +
                '}';
    }
}
