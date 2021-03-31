/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javitronics.javitronics.entity;

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
@Table(name = "producto")
public class ProductoEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String nombre;
    private String codigo;
    private Integer existencias;
    private Double precio;
   
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.REFRESH})
    @JoinColumn(name="id_tipoproducto")
    private TipoproductoEntity tipoproducto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getExistencias() {
        return existencias;
    }

    public void setExistencias(Integer existencias) {
        this.existencias = existencias;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
    @Override
    public String toString() {
        return "ProductoEntity [id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", existencias=" + existencias + ", precio=" + precio  + ", id_tipoproducto=" + tipoproducto.getId() + "]";
    }

    public TipoproductoEntity getTipoproducto() {
        return tipoproducto;
    }

    public void setTipoproducto(TipoproductoEntity tipoproducto) {
        this.tipoproducto = tipoproducto;
    }
    
    
}
