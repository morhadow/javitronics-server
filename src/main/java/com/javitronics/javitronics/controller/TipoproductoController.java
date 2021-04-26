/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javitronics.javitronics.controller;
import java.util.List;
import javax.servlet.http.HttpSession;
import com.javitronics.javitronics.entity.TipoproductoEntity;
import com.javitronics.javitronics.entity.UsuarioEntity;
import com.javitronics.javitronics.repository.TipoproductoRepository;
import com.javitronics.javitronics.service.FillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/tipoproducto")
public class TipoproductoController {

    @Autowired
    HttpSession oHttpSession;

    @Autowired
    TipoproductoRepository oTipoproductoRepository;

    @Autowired
    FillService oFillService;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {
        if (oTipoproductoRepository.existsById(id)) {
            return new ResponseEntity<TipoproductoEntity>(oTipoproductoRepository.getOne(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<TipoproductoEntity>(oTipoproductoRepository.getOne(id), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        if (oTipoproductoRepository.count() <= 1000) {
            return new ResponseEntity<List<TipoproductoEntity>>(oTipoproductoRepository.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.PAYLOAD_TOO_LARGE);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {
        return new ResponseEntity<Long>(oTipoproductoRepository.count(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody TipoproductoEntity oTipoproductoEntity) {
        
        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
       
        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }else{
            if (oUsuarioEntity.getTipoUsuario().getId() == 1) {

                if (oTipoproductoEntity.getId() == null) {
                    return new ResponseEntity<TipoproductoEntity>(oTipoproductoRepository.save(oTipoproductoEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                }

            }else{

                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
            
        }           
        
    }

    @PostMapping("/fill/{amount}")
    public ResponseEntity<?> fill(@PathVariable(value = "amount") Long amount) {
          UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");     
            if (oUsuarioEntity == null) {
                 return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }else{
                 if (oUsuarioEntity.getTipoUsuario().getId() == 1) {
                    return new ResponseEntity<Long>(oFillService.tipoproductoFill(amount), HttpStatus.OK);
                }else{
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

                oTipoproductoRepository.deleteById(id);

                if (oTipoproductoRepository.existsById(id)) {
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
    public ResponseEntity<?> getPage(@PageableDefault(page = 0, size = 10, direction = Direction.ASC) Pageable oPageable) {

        Page<TipoproductoEntity> oPage = oTipoproductoRepository.findAll(oPageable);
        return new ResponseEntity<Page<TipoproductoEntity>>(oPage, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TipoproductoEntity oTipoproductoEntity) {
        oTipoproductoEntity.setId(id);
        if (oTipoproductoRepository.existsById(id)) {
            return new ResponseEntity<TipoproductoEntity>(oTipoproductoRepository.save(oTipoproductoEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
        }
    }

}
