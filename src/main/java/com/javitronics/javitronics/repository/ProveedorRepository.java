/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javitronics.javitronics.repository;

import com.javitronics.javitronics.entity.PedidoEntity;
import com.javitronics.javitronics.entity.ProveedorEntity;
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
public interface ProveedorRepository extends JpaRepository<ProveedorEntity, Long> {
  @Query(value = "SELECT * FROM proveedor c WHERE c.id_pedido = :id_pedido", nativeQuery = true)
    Page<ProveedorEntity> findByProveedorXPedido(Long id_producto, Pageable pageable);

    Page<ProveedorEntity> findByPedido (PedidoEntity oProductoEntity, Pageable oPageable);  
}
