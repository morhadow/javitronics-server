/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javitronics.javitronics.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@Table(name = "compra")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CompraEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    private Integer cantidad;
    private Double precio;
    
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime fecha;
    
    private Integer descuento_usuario;
    private Integer descuento_producto;
    private String codigo_envio;
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.REFRESH})
    @JoinColumn(name="id_factura")
    private FacturaEntity factura;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "id_producto")
    private ProductoEntity producto;
    
    public CompraEntity() {
    }

    public CompraEntity(Long id) {
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

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Integer getDescuento_usuario() {
        return descuento_usuario;
    }

    public void setDescuento_usuario(Integer descuento_usuario) {
        this.descuento_usuario = descuento_usuario;
    }

    public Integer getDescuento_producto() {
        return descuento_producto;
    }

    public void setDescuento_producto(Integer descuento_producto) {
        this.descuento_producto = descuento_producto;
    }

    public String getCodigo_envio() {
        return codigo_envio;
    }

    public void setCodigo_envio(String codigo_envio) {
        this.codigo_envio = codigo_envio;
    }

    
    
    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public FacturaEntity getFactura() {
        return factura;
    }

    public void setFactura(FacturaEntity factura) {
        this.factura = factura;
    }

    
}

