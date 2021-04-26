/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javitronics.javitronics.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author JAVIER
 */
@Entity
@Table(name = "producto")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductoEntity implements Serializable {
        private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String codigo;
    private String nombre;
    private Integer existencias;
    private Double precio;
    private Integer descuento;
   
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.REFRESH})
    @JoinColumn(name="id_tipoproducto")
    private TipoproductoEntity tipoproducto;
    
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "producto", cascade = {CascadeType.REFRESH})
    private List<CompraEntity> compras = new ArrayList<>();
    
     @OneToMany(fetch = FetchType.LAZY, mappedBy = "producto", cascade = {CascadeType.REFRESH})
    private List<CarritoEntity> carritos = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "pedido_producto",
            joinColumns = {
                    @JoinColumn(name = "id_pedido", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "id_producto", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<PedidoEntity> pedido = new HashSet<>();

    public ProductoEntity() {
    }
    
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
    
    
    public Integer getDescuento() {
        return descuento;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }
    
     @Override
    public String toString() {
        return "ProductoEntity [id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", existencias=" + existencias + ", precio=" + precio +  ", descuento=" + descuento + ", id_tipoproducto=" + tipoproducto.getId() + "]";
    }
    
     public int getCompras() {
        return compras.size();
    }

    public int getCarritos() {
        return carritos.size();
    }


    public TipoproductoEntity getTipoproducto() {
        return tipoproducto;
    }

    public void setTipoproducto(TipoproductoEntity tipoproducto) {
        this.tipoproducto = tipoproducto;
    }
    
    
}
