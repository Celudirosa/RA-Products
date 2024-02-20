package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Producto;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductoController {

    // inyeccion por constructor, el autowired ya no se usa tanto
    private final ProductoRepository productoRepository;

    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> getAllProductos(
            @RequestParam(required = false) String name) {

        List<Producto> productos = new ArrayList<Producto>();

        if (name == null) {
            productoRepository.findAll().forEach(productos::add);
        } else {
            productoRepository.findByName(name).forEach(productos::add);
        }

        if (productos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(productos, HttpStatus.OK);

    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> getProductoById(
            @PathVariable("id") int id) {

        Producto producto = productoRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Not found Producto with id = " + id)
        );

        return new ResponseEntity<>(producto, HttpStatus.OK);

    }

    @PostMapping("/productos")
    public ResponseEntity<Producto> createProducto(
            @RequestBody Producto producto) {

        @SuppressWarnings("null")
        Producto _producto = productoRepository.save(producto);

        return new ResponseEntity<>(_producto, HttpStatus.CREATED);

    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> updateProducto(
            @PathVariable("id") int id,
            @RequestBody Producto producto) {

        Producto _producto = productoRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Nor found Producto with id = " + id));
        
        _producto.setName(producto.getName());
        _producto.setDescription(producto.getDescription());
        _producto.setStock(producto.getStock());
        _producto.setPrice(producto.getPrice());
        _producto.setPresentaciones(producto.getPresentaciones());

        return new ResponseEntity<>(productoRepository.save(_producto), HttpStatus.OK);

    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<HttpStatus> deleteProducto(
            @PathVariable("id") int id) {

        productoRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<HttpStatus> deleteAllProductos() {
        productoRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
