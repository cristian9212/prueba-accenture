package com.prueba.franquicias.service.impl;

import com.prueba.franquicias.dto.ProductDto;
import com.prueba.franquicias.model.BranchEntity;
import com.prueba.franquicias.model.ProductEntity;
import com.prueba.franquicias.repository.BranchRepository;
import com.prueba.franquicias.repository.ProductRepository;
import com.prueba.franquicias.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private BranchRepository branchRepository;

    @Override
    public ResponseEntity<?> create(ProductDto productDto) {

        BranchEntity branch=  branchRepository
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
}
