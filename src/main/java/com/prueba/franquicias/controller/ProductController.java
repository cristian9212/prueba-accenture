package com.prueba.franquicias.controller;


import com.prueba.franquicias.controller.doc.ProductDoc;
import com.prueba.franquicias.dto.ProductDto;
import com.prueba.franquicias.dto.ProductStockDto;
import com.prueba.franquicias.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProductController implements ProductDoc {

    private final ProductService productService;

    @Override
    public ResponseEntity<?> create(ProductDto productDto) {
        return productService.create(productDto);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return productService.delete(id);
    }

    @Override
    public ResponseEntity<?> updateStock(Long id,  ProductStockDto productStockDto) {
        return ResponseEntity.ok(productService.updateStock(id, productStockDto.getStock()));
    }

    @Override
    public ResponseEntity<?> findMaxStockByFranchise(Long franchiseId) {
        return ResponseEntity.ok(productService.findMaxStockByFranchise(franchiseId));
    }
}
