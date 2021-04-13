/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javitronics.javitronics.repository;

import com.javitronics.javitronics.entity.CompraEntity;
import com.javitronics.javitronics.entity.FacturaEntity;
import com.javitronics.javitronics.entity.ProductoEntity;
import java.awt.print.Pageable;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JAVIER
 */
@Repository
public interface CompraRepository extends JpaRepository<CompraEntity, Long> {

    @Query(value = "SELECT * FROM compra c WHERE c.id_producto = :id_producto", nativeQuery = true)
    Page<CompraEntity> findByCompraXProducto(Long id_producto, Pageable pageable);

    Page<CompraEntity> findByProducto(ProductoEntity oProductoEntity, Pageable oPageable);

//    public Page<CompraEntity> findById_producto(Long id, Pageable oPageable);
    @Query(value = "SELECT * FROM compra c WHERE c.id_factura = :id_factura", nativeQuery = true)
    Page<CompraEntity> findByCompraXFactura(Long id_factura, Pageable pageable);

    Page<CompraEntity> findByFactura(FacturaEntity oFacturaEntity, Pageable oPageable);

    @Query(value = "SELECT * FROM compra where id_factura IN (SELECT id FROM factura WHERE id_usuario = :id_usuario)", nativeQuery = true)
    Page<CompraEntity> findByCompraXIdUsuario(Long id_usuario, Pageable pageable);    

    List<CompraEntity> findByFactura(FacturaEntity oFacturaEntity);

}
