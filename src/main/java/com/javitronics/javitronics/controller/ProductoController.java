/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javitronics.javitronics.controller;

import com.javitronics.javitronics.entity.ProductoEntity;
import com.javitronics.javitronics.entity.TipoproductoEntity;
import com.javitronics.javitronics.entity.UsuarioEntity;
import com.javitronics.javitronics.repository.ProductoRepository;
import com.javitronics.javitronics.repository.TipoproductoRepository;
import com.javitronics.javitronics.service.FillService;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JAVIER
 */
@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    HttpSession oHttpSession;

    @Autowired
    ProductoRepository oProductoRepository;

    @Autowired
    TipoproductoRepository oTipoproductoRepository;

    @Autowired
    FillService oFillService;
    
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {
        if (oProductoRepository.existsById(id)) {
            return new ResponseEntity<ProductoEntity>(oProductoRepository.getOne(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<ProductoEntity>(oProductoRepository.getOne(id), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        if (oProductoRepository.count() <= 1000) {
            return new ResponseEntity<List<ProductoEntity>>(oProductoRepository.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.PAYLOAD_TOO_LARGE);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {
        return new ResponseEntity<Long>(oProductoRepository.count(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody ProductoEntity oProductoEntity) {
        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipoUsuario().getId() == 1) {
                if (oProductoEntity.getId() == null) {
                    return new ResponseEntity<ProductoEntity>(oProductoRepository.save(oProductoEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ProductoEntity oProductoEntity) {

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipoUsuario().getId() == 1) { //administrador
                oProductoEntity.setId(id);
                if (oProductoRepository.existsById(id)) {
                    return new ResponseEntity<ProductoEntity>(oProductoRepository.save(oProductoEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                }
            } else {  //cliente
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
                return new ResponseEntity<Long>(oFillService.productoFill(amount), HttpStatus.OK);
            } else {
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

                oProductoRepository.deleteById(id);

                if (oProductoRepository.existsById(id)) {
                    return new ResponseEntity<Long>(id, HttpStatus.NOT_MODIFIED);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @GetMapping("/page")
    public ResponseEntity<?> getPage(@RequestParam("filter") Optional<String> strSearch, @PageableDefault(page = 0, size = 10, direction = Direction.ASC) Pageable oPageable
    ) {
        Page<ProductoEntity> oPage = null;
        if (strSearch.isPresent()) {
            oPage = oProductoRepository.findByCodigoContainingIgnoreCaseOrNombreContainingIgnoreCaseOrTipoproductoNombreContainingIgnoreCase(strSearch.get(), strSearch.get(), strSearch.get(), oPageable);
        } else {
            oPage = oProductoRepository.findAll(oPageable);
        }
        return new ResponseEntity<Page<ProductoEntity>>(oPage, HttpStatus.OK);
    }

    @GetMapping("/page/tipoproducto/{id}")
    public ResponseEntity<?> getPageXTipoproducto(@PageableDefault(page = 0, size = 10, direction = Direction.ASC) Pageable oPageable, @PathVariable(value = "id") Long id) {

        if (oTipoproductoRepository.existsById(id)) {
            TipoproductoEntity oTipoproductoEntity = oTipoproductoRepository.getOne(id);
            Page<ProductoEntity> oPage = oProductoRepository.findByTipoproducto(oTipoproductoEntity, oPageable);
            return new ResponseEntity<Page<ProductoEntity>>(oPage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

    }
    
}
