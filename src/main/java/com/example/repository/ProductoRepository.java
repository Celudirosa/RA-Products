package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    // JPA Query Methods
    // https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

    List<Producto> findProductosByPresentacionesId(Integer presentacionId);

    List<Producto> findByName(String name);

}
