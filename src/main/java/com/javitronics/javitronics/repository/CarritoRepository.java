/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javitronics.javitronics.repository;

import com.javitronics.javitronics.entity.CarritoEntity;
import com.javitronics.javitronics.entity.ProductoEntity;
import com.javitronics.javitronics.entity.UsuarioEntity;
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
public interface CarritoRepository extends JpaRepository<CarritoEntity, Long> {

    @Query(value = "SELECT * FROM carrito c WHERE c.id_producto = :id_producto", nativeQuery = true)
    Page<CarritoEntity> findByCarritoXProducto(Long id_producto, org.springframework.data.domain.Pageable pageable);

    Page<CarritoEntity> findByProducto(ProductoEntity oProductoEntity, org.springframework.data.domain.Pageable oPageable);

    Page<CarritoEntity> findByProductoAndUsuario(ProductoEntity oProductoEntity, UsuarioEntity oUsuarioEntity, org.springframework.data.domain.Pageable oPageable);

    @Query(value = "SELECT * FROM carrito c WHERE c.id_usuario = :id_usuario", nativeQuery = true)
    Page<CarritoEntity> findByCarritoXUsuario(Long id_usuario, org.springframework.data.domain.Pageable pageable);

    Page<CarritoEntity> findByUsuario(UsuarioEntity oUsuarioEntity, org.springframework.data.domain.Pageable oPageable);

    List<CarritoEntity> findAllByUsuario(UsuarioEntity oUsuarioEntity);

    Long countByUsuarioAndProducto(UsuarioEntity oUsuarioEntity, ProductoEntity oProductoEntity);

    @Query(value = "SELECT * FROM carrito c WHERE c.id_usuario = :id_usuario and c.id_producto = :id_producto", nativeQuery = true)
    CarritoEntity findByUsuarioAndProducto(Long id_usuario, Long id_producto);

    Long deleteByUsuario(UsuarioEntity oUsuarioEntity);

    Long deleteByIdAndUsuario(Long id, UsuarioEntity oUsuarioEntity);

    CarritoEntity findByIdAndUsuario(Long idCarrito, UsuarioEntity oUsuarioEntity);
}