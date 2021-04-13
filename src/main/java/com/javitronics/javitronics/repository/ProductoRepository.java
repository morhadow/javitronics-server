/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javitronics.javitronics.repository;

import com.javitronics.javitronics.entity.ProductoEntity;
import com.javitronics.javitronics.entity.TipoproductoEntity;
import java.awt.print.Pageable;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author JAVIER
 */
@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {

    @Query(value = "SELECT * FROM productos p WHERE p.id_tipoproducto = :id_tipoproducto", nativeQuery = true)
    Page<ProductoEntity> findByCompraXFactura(Long id_tipoproducto, Pageable pageable);

    Page<ProductoEntity> findByTipoproducto(TipoproductoEntity oTipoproductoEntity, Pageable oPageable);

    
    List<ProductoEntity> findTop10ByOrderByDescuentoDesc();
    List<ProductoEntity> findTop50ByOrderByDescuentoDesc();
    List<ProductoEntity> findTop100ByOrderByDescuentoDesc();
    List<ProductoEntity> findTop10ByOrderByDescuentoAsc();
    List<ProductoEntity> findTop50ByOrderByDescuentoAsc();
    List<ProductoEntity> findTop100ByOrderByDescuentoAsc();


    Page<ProductoEntity> findByCodigoContainingIgnoreCaseOrNombreContainingIgnoreCaseOrTipoproductoNombreContainingIgnoreCase(String codigo, String descripcion, String nombre, Pageable pageable);


}
