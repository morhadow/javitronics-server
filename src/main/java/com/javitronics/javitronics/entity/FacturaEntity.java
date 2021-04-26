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
@Table(name = "factura")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class FacturaEntity implements Serializable{
    
     private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime fecha;
    
    private Integer iva;
    private Boolean pagado;
    
    @OneToMany(fetch=FetchType.LAZY,mappedBy="factura", cascade={CascadeType.REFRESH})
    private List<CompraEntity> compra = new ArrayList<>();

    public FacturaEntity() {  
    }

    public FacturaEntity(Long id) {
        this.id = id;
    }
 
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Integer getIva() {
        return iva;
    }

    public void setIva(Integer iva) {
        this.iva = iva;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public Boolean getPagado() {
        return pagado;
    }

    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
    }

    @Override
    public String toString() {
        return "FacturaEntity [id=" + id + ", fecha=" + fecha + ", iva=" + iva + ", id_usuario=" + usuario.getId() + ", pagado=" + pagado + "]";
    }
    
    public int getCompras() {
    return compra.size();
}
    
    
    
}
