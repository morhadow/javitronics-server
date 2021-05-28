/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javitronics.javitronics.controller;
import java.util.List;
import javax.servlet.http.HttpSession;
import com.javitronics.javitronics.entity.TipoUsuarioEntity;
import com.javitronics.javitronics.repository.TipoUsuarioRepository;
import com.javitronics.javitronics.service.FillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
    TipoUsuarioRepository oTipousuarioRepository;

    @Autowired
    FillService oFillService;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {
        if (oTipousuarioRepository.existsById(id)) {
            return new ResponseEntity<TipoUsuarioEntity>(oTipousuarioRepository.getOne(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<TipoUsuarioEntity>(oTipousuarioRepository.getOne(id), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        if (oTipousuarioRepository.count() <= 1000) {
            return new ResponseEntity<List<TipoUsuarioEntity>>(oTipousuarioRepository.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.PAYLOAD_TOO_LARGE);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {
        return new ResponseEntity<Long>(oTipousuarioRepository.count(), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> getPage(@PageableDefault(page = 0, size = 10, direction = Direction.ASC) Pageable oPageable) {
        Page<TipoUsuarioEntity> oPage = oTipousuarioRepository.findAll(oPageable);
        return new ResponseEntity<Page<TipoUsuarioEntity>>(oPage, HttpStatus.OK);
    }
}
