/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javitronics.javitronics.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author JAVIER
 */
@Entity
@Table(name = "usuario")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UsuarioEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String login;
    
    @JsonIgnore
    private String password;
    
    private String email;
    private Integer descuento;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.REFRESH})
    @JoinColumn(name="id_tipousuario")
    private TipoUsuarioEntity tipousuario;
    
    @JsonIgnore
    private String token;
    @JsonIgnore
    private boolean validado;
    @JsonIgnore
    private boolean activo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = {CascadeType.REFRESH})
    private List<FacturaEntity> facturas = new ArrayList<>();
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = {CascadeType.REFRESH})
    private List<CarritoEntity> carritos = new ArrayList<>();

    public UsuarioEntity() {
    }

    public UsuarioEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getDescuento() {
        return descuento;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public TipoUsuarioEntity getTipoUsuario() {
        return tipousuario;
    }

    public void setTipoUsuario(TipoUsuarioEntity tipousuario) {
        this.tipousuario = tipousuario;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ",dni=" + dni + ",nombre=" + nombre + ",apellido1=" + apellido1 + ",login=" + login
                + ",email=" + email + ",descuento=" + descuento + "]";
    }

    public int getCarritos() {
        return carritos.size();
    }

    public int getFacturas() {
        return facturas.size();
    }
}