/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javitronics.javitronics.controller;

import com.javitronics.javitronics.entity.TipoUsuarioEntity;
import com.javitronics.javitronics.repository.TipoUsuarioRepository;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JAVIER
 */
@RestController
@RequestMapping("/tipousuario")
public class TipoUsuarioController {
   @Autowired
    HttpSession oHttpSession;

    @Autowired
    TipoUsuarioRepository oTipoUsuarioRepository;

    
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {
        if (oTipoUsuarioRepository.existsById(id)) {
            return new ResponseEntity<TipoUsuarioEntity>(oTipoUsuarioRepository.getOne(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<TipoUsuarioEntity>(oTipoUsuarioRepository.getOne(id), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        if (oTipoUsuarioRepository.count() <= 1000) {
            return new ResponseEntity<List<TipoUsuarioEntity>>(oTipoUsuarioRepository.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.PAYLOAD_TOO_LARGE);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {
        return new ResponseEntity<Long>(oTipoUsuarioRepository.count(), HttpStatus.OK);
    }   
}
