/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javitronics.javitronics.repository;

import com.javitronics.javitronics.entity.PedidoEntity;
import com.javitronics.javitronics.entity.ProductoEntity;
import java.awt.print.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JAVIER
 */
@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
  @Query(value = "SELECT * FROM pedido c WHERE c.id_producto = :id_producto", nativeQuery = true)
    Page<PedidoEntity> findByPedidoXProducto(Long id_producto, Pageable pageable);

    Page<PedidoEntity> findByProducto(ProductoEntity oProductoEntity, Pageable oPageable);  
}
