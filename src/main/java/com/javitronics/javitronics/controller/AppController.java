/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javitronics.javitronics.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JAVIER
 */
@RestController
@RequestMapping("/")
public class AppController {

    @Autowired
    HttpSession oHttpSession;
   
    @GetMapping("/")
    public ResponseEntity<String> info() {
        return new ResponseEntity<String>("Bienvenido al Servidor de Javitronics", HttpStatus.OK);
    }
   
}
