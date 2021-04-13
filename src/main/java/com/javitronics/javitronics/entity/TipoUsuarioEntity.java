/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javitronics.javitronics.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 *
 * @author JAVIER
 */
@Entity
@Table(name = "tipousuario")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class TipoUsuarioEntity implements Serializable{
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
   private Long id;
    private String nombre;
    
    
@OneToMany(fetch=FetchType.LAZY,mappedBy="tipousuario", cascade={CascadeType.REFRESH})
    private List<UsuarioEntity> usuarios = new ArrayList<>();
  
   public TipoUsuarioEntity() {
    }

    public TipoUsuarioEntity(Long id) {
        this.id = id;
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
    
    @Override
    public String toString(){
        return "TipousuarioEntity [id=" + id + ", nombre=" + nombre + "]";
    }

    public int getUsuarios() {
        return usuarios.size();
    }
}
