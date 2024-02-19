package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Presentacion;
import com.example.entities.Producto;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.PresentacionRepository;
import com.example.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PresentacionController {

    private final ProductoRepository productoRepository;
    private final PresentacionRepository presentacionRepository;

    @GetMapping("/presentaciones")
    public ResponseEntity<List<Presentacion>> getAllDescripciones() {
        List<Presentacion> presentaciones = new ArrayList<>();

        presentacionRepository.findAll().forEach(presentaciones::add);

        if (presentaciones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(presentaciones, HttpStatus.OK);

    }

    @SuppressWarnings("null")
    @GetMapping("/productos/{productoId}/presentaciones")
    public ResponseEntity<List<Presentacion>> getAllPresentacionesByProductoId(
            @PathVariable(value = "productoId") Integer productoId) {
        if (!productoRepository.existsById(productoId)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + productoId);
        }

        List<Presentacion> presentaciones = presentacionRepository.findPresentaciones   ByProductosId(productoId);
        return new ResponseEntity<>(presentaciones, HttpStatus.OK);
    }


}
