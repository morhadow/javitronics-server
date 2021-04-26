/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javitronics.javitronics.controller;
import com.javitronics.javitronics.entity.FacturaEntity;
import com.javitronics.javitronics.entity.UsuarioEntity;
import com.javitronics.javitronics.repository.FacturaRepository;
import com.javitronics.javitronics.repository.UsuarioRepository;
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
@RequestMapping("/factura")

public class FacturaController {

    @Autowired
    HttpSession oHttpSession;

    @Autowired
    FacturaRepository oFacturaRepository;

    @Autowired
    UsuarioRepository oUsuarioRepository;

    @Autowired
    FillService oFillService;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {
        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        FacturaEntity oFacturaEntity = new FacturaEntity();

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipoUsuario().getId() == 1) { //administrador
                if (oFacturaRepository.existsById(id)) {
                    return new ResponseEntity<FacturaEntity>(oFacturaRepository.getOne(id), HttpStatus.OK);
                } else {
                    return new ResponseEntity<FacturaEntity>(oFacturaRepository.getOne(id), HttpStatus.NOT_FOUND);
                }
            } else {  //cliente
                oFacturaEntity = oFacturaRepository.getOne(id);
                if (oFacturaEntity != null) {
                    if (oFacturaEntity.getUsuario().getId().equals(oUsuarioEntity.getId())) {
                        return new ResponseEntity<FacturaEntity>(oFacturaRepository.getOne(id), HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
                    }
                } else {
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
//                if (oFacturaEntity.getUsuario().getId().equals(oUsuarioEntity.getId())) {  //los datos pedidos por el cliente son sus propios datos?
//                    return new ResponseEntity<FacturaEntity>(oFacturaRepository.getOne(id), HttpStatus.OK);
//                } else {
//                    return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
//                }
            }
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        if (oFacturaRepository.count() <= 1000) {
            return new ResponseEntity<List<FacturaEntity>>(oFacturaRepository.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.PAYLOAD_TOO_LARGE);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody FacturaEntity oFacturaEntity) {

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {

            if (oUsuarioEntity.getTipoUsuario().getId() == 1) { //administrador

                if (oFacturaEntity.getId() == null) {
                    return new ResponseEntity<FacturaEntity>(oFacturaRepository.save(oFacturaEntity), HttpStatus.OK);
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

            if (oUsuarioEntity.getTipoUsuario().getId() == 1) { //administrador

                oFacturaRepository.deleteById(id);
                if (oFacturaRepository.existsById(id)) {
                    return new ResponseEntity<Long>(id, HttpStatus.NOT_MODIFIED);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.OK);
                }

            } else {  //cliente

                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }

    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {
        return new ResponseEntity<Long>(oFacturaRepository.count(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody FacturaEntity oFacturaEntity) {

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {

            if (oUsuarioEntity.getTipoUsuario().getId() == 1) { //administrador

                oFacturaEntity.setId(id);
                if (oFacturaRepository.existsById(id)) {
                    return new ResponseEntity<FacturaEntity>(oFacturaRepository.save(oFacturaEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                }

            } else {  //cliente

                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }

    }

    @GetMapping("/page")
    public ResponseEntity<?> getPage(@PageableDefault(page = 0, size = 10, direction = Direction.ASC) Pageable oPageable) {

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
//        FacturaEntity oFacturaEntity = new FacturaEntity();

        Page<FacturaEntity> oPage = oFacturaRepository.findAll(oPageable);
        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipoUsuario().getId() == 1) { //administrador
                return new ResponseEntity<Page<FacturaEntity>>(oPage, HttpStatus.OK);
            } else {  //cliente
//                if (oFacturaEntity2.getUsuario().getId().equals(oUsuarioEntity.getId())) {  //los datos pedidos por el cliente son sus propios datos?
//                    return new ResponseEntity<Page<FacturaEntity>>(oPage, HttpStatus.OK);
                return new ResponseEntity<Page<FacturaEntity>>(oFacturaRepository.findByFacturaXUsuario(oUsuarioEntity.getId(), oPageable), HttpStatus.OK);
//                } else {
//                    return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
//                }
            }
        }
    }

    @GetMapping("/pagexusuario/{id}")
    public ResponseEntity<?> getPageXUsuario(@PageableDefault(page = 0, size = 10, direction = Direction.ASC) Pageable oPageable, @PathVariable(value = "id") Long id) {

        if (oUsuarioRepository.existsById(id)) {
            UsuarioEntity oUsuarioEntity = oUsuarioRepository.getOne(id);
            Page<FacturaEntity> oPage = oFacturaRepository.findByUsuario(oUsuarioEntity, oPageable);
            return new ResponseEntity<Page<FacturaEntity>>(oPage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @GetMapping("/allxusuario/{id}")
    public ResponseEntity<?> getAllXUsuario(@PathVariable(value = "id") Long id) {
        if (oUsuarioRepository.existsById(id)) {
            UsuarioEntity oUsuarioEntity = oUsuarioRepository.getOne(id);
            if (oUsuarioEntity.getTipoUsuario().getId() > 1) {
                List<FacturaEntity> oPage = oFacturaRepository.findByUsuario(oUsuarioEntity);
                return new ResponseEntity<List<FacturaEntity>>(oPage, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }


    @PostMapping("/fill/{amount}")
    public ResponseEntity<?> fill(@PathVariable(value = "amount") Long amount) {
        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipoUsuario().getId() == 1) {
                return new ResponseEntity<Long>(oFillService.facturaFill(amount), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }


    @GetMapping("/page/usuario/{id}")
    public ResponseEntity<?> getPageXusuario(@PageableDefault(page = 0, size = 10, direction = Direction.ASC) Pageable oPageable, @PathVariable(value = "id") Long id) {

        if (oUsuarioRepository.existsById(id)) {
            UsuarioEntity oUsuarioEntity = oUsuarioRepository.getOne(id);
            Page<FacturaEntity> oPage = oFacturaRepository.findByUsuario(oUsuarioEntity, oPageable);
            return new ResponseEntity<Page<FacturaEntity>>(oPage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

    }

}
