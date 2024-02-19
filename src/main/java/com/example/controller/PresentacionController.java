package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Presentacion;
import com.example.repository.PresentacionRepository;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PresentacionController {

    private final ProductoController productoController;
    private final PresentacionRepository presentacionRepository;

    @GetMapping("/tags")
    public ResponseEntity<List<Presentacion>> getAllDescripciones() {
        List<Presentacion> presentaciones = new ArrayList<>();

        presentacionRepository.findAll().forEach(presentaciones::add);

        if (presentaciones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(presentaciones, HttpStatus.OK);

    }

}
