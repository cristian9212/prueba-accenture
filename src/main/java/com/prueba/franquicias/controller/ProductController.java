package com.prueba.franquicias.controller;


import com.prueba.franquicias.controller.doc.ProductDoc;
import com.prueba.franquicias.dto.ProductDto;
import com.prueba.franquicias.model.ProductEntity;
import com.prueba.franquicias.service.FranchiseService;
import com.prueba.franquicias.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProductController implements ProductDoc {

    private final ProductService ProductService;

    @Override
    public ResponseEntity<?> create(ProductDto productDto) {


        return this.ProductService.create(productDto);
    }
}
