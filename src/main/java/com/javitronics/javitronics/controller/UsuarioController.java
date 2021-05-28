/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javitronics.javitronics.controller;

import com.javitronics.javitronics.entity.TipoUsuarioEntity;
import com.javitronics.javitronics.entity.UsuarioEntity;
import com.javitronics.javitronics.helper.RandomHelper;
import com.javitronics.javitronics.repository.TipoUsuarioRepository;
import com.javitronics.javitronics.repository.UsuarioRepository;
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
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    HttpSession oHttpSession;

    @Autowired
    UsuarioRepository oUsuarioRepository;

    @Autowired
    TipoUsuarioRepository oTipousuarioRepository;

    @Autowired
    FillService oFillService;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {
        UsuarioEntity oUsuarioEntityFromSession = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntityFromSession == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntityFromSession.getTipoUsuario().getId() == 1) { //administrador
                if (oUsuarioRepository.existsById(id)) {
                    return new ResponseEntity<UsuarioEntity>(oUsuarioRepository.getOne(id), HttpStatus.OK);
                } else {
                    return new ResponseEntity<UsuarioEntity>(oUsuarioRepository.getOne(id), HttpStatus.NOT_FOUND);
                }
            } else {  //cliente
                if (id.equals(oUsuarioEntityFromSession.getId())) {  //los datos pedidos por el cliente son sus propios datos?
                    return new ResponseEntity<UsuarioEntity>(oUsuarioRepository.getOne(id), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
                }
            }

        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> get() {
        UsuarioEntity oUsuarioEntityFromSession = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntityFromSession == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntityFromSession.getTipoUsuario().getId() == 1) {
                if (oUsuarioRepository.count() <= 1000) {
                    return new ResponseEntity<List<UsuarioEntity>>(oUsuarioRepository.findAll(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(null, HttpStatus.PAYLOAD_TOO_LARGE);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody UsuarioEntity oUsuarioEntityFromRequest) {
        UsuarioEntity oUsuarioEntityFromSession = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntityFromSession == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntityFromSession.getTipoUsuario().getId() == 1) {
                oUsuarioEntityFromRequest.setId(null);
                if (oUsuarioEntityFromRequest.getPassword() == null) {
                    oUsuarioEntityFromRequest.setPassword("da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04"); //trolleyes
                }
                oUsuarioEntityFromRequest.setToken(RandomHelper.getRandomHexString(20));
                oUsuarioEntityFromRequest.setActivo(false);
                oUsuarioEntityFromRequest.setValidado(false);
                if (oUsuarioEntityFromRequest.getDescuento() == null) {
                    oUsuarioEntityFromRequest.setDescuento(0);
                }
                return new ResponseEntity<UsuarioEntity>(oUsuarioRepository.save(oUsuarioEntityFromRequest), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {
        UsuarioEntity oUsuarioEntityFromSession = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntityFromSession == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntityFromSession.getTipoUsuario().getId() == 1) { //administrador
                return new ResponseEntity<Long>(oUsuarioRepository.count(), HttpStatus.OK);
            } else {  //cliente
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @PostMapping("/fill/{amount}")
    public ResponseEntity<?> fill(@PathVariable(value = "amount") Long amount) {
        UsuarioEntity oUsuarioEntityFromSession = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntityFromSession == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntityFromSession.getTipoUsuario().getId() == 1) {
                return new ResponseEntity<Long>(oFillService.usuarioFill(amount), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        UsuarioEntity oUsuarioEntityFromSession = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntityFromSession == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntityFromSession.getTipoUsuario().getId() == 1) { //administrador
                oUsuarioRepository.deleteById(id);
                if (oUsuarioRepository.existsById(id)) {
                    return new ResponseEntity<Long>(id, HttpStatus.NOT_MODIFIED);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.OK);
                }
            } else {  //cliente
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody UsuarioEntity oUsuarioEntityFromRequest) {
        UsuarioEntity oUsuarioEntityFromSession = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntityFromSession == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntityFromSession.getTipoUsuario().getId() == 1) { //administrador
                if (oUsuarioRepository.existsById(id)) {
                    UsuarioEntity oUsuarioEntityFromDatabase = oUsuarioRepository.getOne(id);
                    oUsuarioEntityFromRequest.setPassword(oUsuarioEntityFromDatabase.getPassword());
                    oUsuarioEntityFromRequest.setToken(oUsuarioEntityFromDatabase.getToken());
                    oUsuarioEntityFromRequest.setActivo(oUsuarioEntityFromDatabase.isActivo());
                    oUsuarioEntityFromRequest.setValidado(oUsuarioEntityFromDatabase.isValidado());
                    return new ResponseEntity<UsuarioEntity>(oUsuarioRepository.save(oUsuarioEntityFromRequest), HttpStatus.OK);
                } else {
                    return new ResponseEntity<UsuarioEntity>(oUsuarioRepository.getOne(id), HttpStatus.NOT_FOUND);
                }
            } else {  //cliente
                if (oUsuarioEntityFromSession.getId() == id) {
                    UsuarioEntity oUsuarioEntityFromDatabase = oUsuarioRepository.getOne(id);
                    oUsuarioEntityFromRequest.setPassword(oUsuarioEntityFromDatabase.getPassword());
                    oUsuarioEntityFromRequest.setToken(oUsuarioEntityFromDatabase.getToken());
                    oUsuarioEntityFromRequest.setActivo(oUsuarioEntityFromDatabase.isActivo());
                    oUsuarioEntityFromRequest.setValidado(oUsuarioEntityFromDatabase.isValidado());
                    return new ResponseEntity<UsuarioEntity>(oUsuarioRepository.save(oUsuarioEntityFromRequest), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
                }
            }
        }
    }

    @GetMapping("/page")
    public ResponseEntity<?> getPage(@RequestParam("filter") Optional<String> strSearch, @PageableDefault(page = 0, size = 10, direction = Direction.ASC) Pageable oPageable) {
        Page<UsuarioEntity> oPage = null;
        UsuarioEntity oUsuarioEntityFromSession = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntityFromSession == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntityFromSession.getTipoUsuario().getId() == 1) { //administrador
                if (strSearch.isPresent()) {
                    oPage = oUsuarioRepository.findByDniContainingIgnoreCaseOrNombreContainingIgnoreCaseOrApellido1ContainingIgnoreCaseOrApellido2ContainingIgnoreCaseOrLoginContainingIgnoreCase(strSearch.get(), strSearch.get(), strSearch.get(), strSearch.get(), strSearch.get(), oPageable);
                } else {
                    oPage = oUsuarioRepository.findAll(oPageable);
                }
                return new ResponseEntity<Page<UsuarioEntity>>(oPage, HttpStatus.OK);
            } else {  //cliente
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @GetMapping("/page/tipousuario/{id}")
    public ResponseEntity<?> getPageXTipousuario(@PageableDefault(page = 0, size = 10, direction = Direction.ASC) Pageable oPageable, @PathVariable(value = "id") Long id) {
        UsuarioEntity oUsuarioEntityFromSession = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntityFromSession == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oTipousuarioRepository.existsById(id)) {
                TipoUsuarioEntity oTipousuarioEntity = oTipousuarioRepository.getOne(id);
                Page<UsuarioEntity> oPage = oUsuarioRepository.findByTipoUsuario(oTipousuarioEntity, oPageable);
                return new ResponseEntity<Page<UsuarioEntity>>(oPage, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
        }
    }

}
