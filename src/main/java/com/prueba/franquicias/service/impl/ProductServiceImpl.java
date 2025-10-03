package com.prueba.franquicias.service.impl;


import com.prueba.franquicias.dto.ProductDto;
import com.prueba.franquicias.dto.response.TopProductDTO;
import com.prueba.franquicias.model.BranchEntity;
import com.prueba.franquicias.model.ProductEntity;
import com.prueba.franquicias.repository.BranchRepository;
import com.prueba.franquicias.repository.FranchiseRepository;
import com.prueba.franquicias.repository.ProductRepository;
import com.prueba.franquicias.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;
    private final FranchiseRepository franchiseRepository;

    @Override
    public ResponseEntity<?> create(ProductDto productDto) {
        BranchEntity branch = branchRepository
                .findById(productDto.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch not found with id: " + productDto.getBranchId()));

        ProductEntity productEntity = ProductEntity.builder()
                .name(productDto.getName())
                .stock(productDto.getStock())
                .version(productDto.getVersion())
                .branch(branch)
                .build();

        ProductEntity newProduct = productRepository.save(productEntity);
        return ResponseEntity.ok(newProduct);
    }

    @Override
    public ResponseEntity<?> delete(Long productId) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        productRepository.delete(product);
        return ResponseEntity.ok("Product with id " + productId + " was deleted successfully");
    }

    @Override
    public ResponseEntity<?> updateStock(Long id, int stock) {
        Optional<ProductEntity> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        ProductEntity product = optionalProduct.get();
        product.setStock(stock);
        productRepository.save(product);
        return ResponseEntity.ok("Stock updated successfully");
    }

    @Override
    public List<TopProductDTO> findMaxStockByFranchise(Long franchiseId) {

        branchRepository
                .findById(franchiseId)
                .orElseThrow(
                        () -> new RuntimeException("Branch not found with id: " + franchiseId)
                );

        return productRepository.findTopPerBranchJpql(franchiseId);

    }
}
