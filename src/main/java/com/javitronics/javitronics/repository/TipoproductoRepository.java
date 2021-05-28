/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javitronics.javitronics.repository;

import com.javitronics.javitronics.entity.TipoproductoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JAVIER
 */
@Repository
public interface TipoproductoRepository extends JpaRepository<TipoproductoEntity, Long>  {
      Page<TipoproductoEntity> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
      
}
