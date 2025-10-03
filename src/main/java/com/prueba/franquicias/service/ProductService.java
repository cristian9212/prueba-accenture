package com.prueba.franquicias.service;

import com.prueba.franquicias.dto.ProductDto;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<?> create(ProductDto productDto);
}
