package com.example.apiproductostiendaconspringsecurity.producto;

import javax.persistence.*;

@Entity
@Table(name="productos")
public class ProductoEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer producto_id;
    private String nombre;
    private Double precio;
    private Integer stock;

    public ProductoEntity() {
    }

    public ProductoEntity(String nombre, Double precio, Integer stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public ProductoEntity(Integer producto_id, String nombre, Double precio, Integer stock) {
        this.producto_id = producto_id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public Integer getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(Integer producto_id) {
        this.producto_id = producto_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
