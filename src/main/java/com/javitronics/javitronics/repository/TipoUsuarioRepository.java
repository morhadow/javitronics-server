/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javitronics.javitronics.repository;

import com.javitronics.javitronics.entity.TipoUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author JAVIER
 */
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuarioEntity, Long> {
    
}
