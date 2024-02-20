package com.example.utilities;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.entities.Presentacion;
import com.example.entities.Producto;
import com.example.repository.PresentacionRepository;
import com.example.repository.ProductoRepository;

@Configuration
public class LoadSampleData {

    @Bean
    public CommandLineRunner saveSampleData(ProductoRepository productoRepository, PresentacionRepository presentacionRepository) {
    
        return datas -> {

            // presentaciones
            presentacionRepository.save(Presentacion.builder()
                .name("Unidad")
                .build()
            );

            presentacionRepository.save(Presentacion.builder()
                .name("Docena")
                .build()
            );

            // productos
            productoRepository.save(Producto.builder()
                .name("Tijeras")
                .description("Con punta redonda")
                .stock(10)
                .price(3)
                .build()
            );

        };
    }

}
