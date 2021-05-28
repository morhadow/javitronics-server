/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javitronics.javitronics.controller;

import com.javitronics.javitronics.entity.PedidoEntity;
import com.javitronics.javitronics.entity.ProductoEntity;
import com.javitronics.javitronics.entity.UsuarioEntity;
import com.javitronics.javitronics.repository.PedidoRepository;
import com.javitronics.javitronics.repository.ProductoRepository;
import com.javitronics.javitronics.service.FillService;
import java.awt.print.Pageable;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JAVIER
 */

@RestController
@RequestMapping("/pedido")

public class PedidoController {
@Autowired
HttpSession oHttpSession;
     
@Autowired
PedidoRepository oPedidoRepository; 

@Autowired
ProductoRepository oProductoRepository;

@Autowired
 FillService oFillService;

@GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {
        if (oPedidoRepository.existsById(id)) {
            return new ResponseEntity<PedidoEntity>(oPedidoRepository.getOne(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<PedidoEntity>(oPedidoRepository.getOne(id), HttpStatus.NOT_FOUND);
        }
    } 
    
    
    
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody PedidoEntity oPedidoEntity) {
        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipoUsuario().getId() == 1) {
                if (oPedidoEntity.getId() == null) {
                    return new ResponseEntity<PedidoEntity>(oPedidoRepository.save(oPedidoEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<?> all() {
        if (oPedidoRepository.count() <= 1000) {
            return new ResponseEntity<List<PedidoEntity>>(oPedidoRepository.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.PAYLOAD_TOO_LARGE);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {
        return new ResponseEntity<Long>(oPedidoRepository.count(), HttpStatus.OK);
    }
    
      @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody PedidoEntity oPedidoEntity) {

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipoUsuario().getId() == 1) { //administrador
                oPedidoEntity.setId(id);
                if (oPedidoRepository.existsById(id)) {
                    return new ResponseEntity<PedidoEntity>(oPedidoRepository.save(oPedidoEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                }
            } else {  //cliente
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }

    }
    
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipoUsuario().getId() == 1) {

                oPedidoRepository.deleteById(id);

                if (oPedidoRepository.existsById(id)) {
                    return new ResponseEntity<Long>(id, HttpStatus.NOT_MODIFIED);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }
    
    
    @PostMapping("/fill/{amount}")
    public ResponseEntity<?> fill(@PathVariable(value = "amount") Long amount) {
        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipoUsuario().getId() == 1) {
                return new ResponseEntity<Long>(oFillService.pedidoFill(amount), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }
    
    
    @GetMapping("/page/producto/{id}")
    public ResponseEntity<?> getPageXTipoproducto(@PageableDefault(page = 0, size = 10, direction = Direction.ASC) Pageable oPageable, @PathVariable(value = "id") Long id) {

        if (oProductoRepository.existsById(id)) {
            ProductoEntity oProductoEntity = oProductoRepository.getOne(id);
            Page<PedidoEntity> oPage = oPedidoRepository.findByProducto(oProductoEntity, oPageable);
            return new ResponseEntity<Page<PedidoEntity>>(oPage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

    }
    
}


