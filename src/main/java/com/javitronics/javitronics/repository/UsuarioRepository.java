/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javitronics.javitronics.repository;

import com.javitronics.javitronics.entity.TipoUsuarioEntity;
import com.javitronics.javitronics.entity.UsuarioEntity;
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
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    UsuarioEntity findByLoginAndPassword(String login, String password);

    Page<UsuarioEntity> findByDniContainingIgnoreCaseOrNombreContainingIgnoreCaseOrApellido1ContainingIgnoreCaseOrApellido2ContainingIgnoreCaseOrLoginContainingIgnoreCase(String dni, String nombre, String apellido1, String apellido2, String login, Pageable pageable);

    @Query(value = "SELECT * FROM usuario u WHERE c.id_tipousuario = :id_tipousuario", nativeQuery = true)
    Page<UsuarioEntity> findByUsuarioXTipoUsuario(Long id_tipousuario, Pageable pageable);

    Page<UsuarioEntity> findByTipoUsuario(TipoUsuarioEntity oTipousuarioEntity, Pageable oPageable);

    public Page<UsuarioEntity> findAll(Pageable oPageable);
}
