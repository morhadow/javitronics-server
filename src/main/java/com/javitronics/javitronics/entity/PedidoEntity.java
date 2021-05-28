/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javitronics.javitronics.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.Table;
/**
 *
 * @author JAVIER
 */
   @Entity
@Table(name = "pedido")
public class PedidoEntity implements Serializable{
               private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") 
    private Long id;
    private String codigo;
    private Integer cantidad;
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime fecha;
    
    
    @ManyToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
    private Set<ProductoEntity> producto = new HashSet<>();
    
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "pedido_proveedor",
            joinColumns = {
                    @JoinColumn(name = "id_pedido", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "id_proveedor", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<ProveedorEntity> proveedor = new HashSet<>();
    
public PedidoEntity() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Set<ProductoEntity> getProducto() {
        return producto;
    }

    public void setProducto(Set<ProductoEntity> producto) {
        this.producto = producto;
    }

    public Set<ProveedorEntity> getProveedor() {
        return proveedor;
    }

    public void setProveedor(Set<ProveedorEntity> proveedor) {
        this.proveedor = proveedor;
    }
    
    
    
}
