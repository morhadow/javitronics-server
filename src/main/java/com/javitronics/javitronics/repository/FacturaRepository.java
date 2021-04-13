/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javitronics.javitronics.repository;

import com.javitronics.javitronics.entity.FacturaEntity;
import com.javitronics.javitronics.entity.UsuarioEntity;
import java.awt.print.Pageable;
import java.time.LocalDateTime;
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
public interface FacturaRepository extends JpaRepository<FacturaEntity, Long> {
    
    @Query(value = "SELECT * FROM factura f WHERE f.id_usuario = :id_usuario", nativeQuery = true)
    Page<FacturaEntity> findByFacturaXUsuario(Long id_usuario, Pageable pageable);

    Page<FacturaEntity> findByUsuario(UsuarioEntity oUsuarioEntity, Pageable oPageable);
    
    List<FacturaEntity> findByUsuario(UsuarioEntity oUsuarioEntity);
    List<FacturaEntity> findTop10ByUsuarioOrderByFechaDesc(UsuarioEntity oUsuarioEntity);
    List<FacturaEntity> findTop10ByUsuarioAndFechaBetweenOrderByFechaDesc(UsuarioEntity oUsuarioEntity,LocalDateTime fini,LocalDateTime ffin);
    List<FacturaEntity> findTop10ByUsuarioAndFechaGreaterThanEqualAndFechaLessThanEqualOrderByFechaDesc(UsuarioEntity oUsuarioEntity,LocalDateTime fini,LocalDateTime ffin);
    List<FacturaEntity> findTop100ByUsuarioAndFechaGreaterThanEqualAndFechaLessThanEqualOrderByFechaDesc(UsuarioEntity oUsuarioEntity,LocalDateTime fini,LocalDateTime ffin);
    List<FacturaEntity> findTop1000ByUsuarioAndFechaGreaterThanEqualAndFechaLessThanEqualOrderByFechaDesc(UsuarioEntity oUsuarioEntity,LocalDateTime fini,LocalDateTime ffin);
}
