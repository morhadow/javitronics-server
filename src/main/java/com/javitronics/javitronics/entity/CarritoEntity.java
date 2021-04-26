/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javitronics.javitronics.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author JAVIER
 */
@Entity
@Table(name = "carrito")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class CarritoEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Integer cantidad;
    private Double precio;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "id_producto")
    private ProductoEntity producto;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;
   
    public CarritoEntity(){
    }
    
    public CarritoEntity(Long id){
        this.id = id;
    }
  
    public Long getId() {
        return id;
    }

   
    public void setId(Long id) {
        this.id = id;
    }

    
    public Integer getCantidad() {
        return cantidad;
    }

    
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    
    public Double getPrecio() {
        return precio;
    }

   
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    
    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }


  }
