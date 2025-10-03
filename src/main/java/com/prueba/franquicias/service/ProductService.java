package com.prueba.franquicias.service;

import com.prueba.franquicias.dto.ProductDto;
import com.prueba.franquicias.dto.ProductMaxStockDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    ResponseEntity<?> create(ProductDto productDto);

    ResponseEntity<?> delete(Long productId);

    ResponseEntity<?> updateStock(Long id, int stock);

    List<ProductMaxStockDto> findMaxStockByFranchise(Long franchiseId);
}

