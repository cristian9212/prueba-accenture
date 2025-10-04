package com.prueba.franquicias.controller;

import com.prueba.franquicias.dto.ProductDto;
import com.prueba.franquicias.dto.ProductStockDto;

import com.prueba.franquicias.dto.response.TopProductDTO;
import com.prueba.franquicias.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private ProductDto productDto;
    private ProductStockDto productStockDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        productDto = new ProductDto();
        productDto.setName("Café");
        productDto.setStock(10);

        productStockDto = new ProductStockDto();
        productStockDto.setStock(20);
    }

    @Test
    void testCreate() {
        ResponseEntity response = ResponseEntity.status(HttpStatus.CREATED).body(productDto);

        when(productService.create(any(ProductDto.class))).thenReturn(response);

        ResponseEntity<?> result = productController.create(productDto);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("Café", ((ProductDto) result.getBody()).getName());
        verify(productService, times(1)).create(productDto);
    }

    @Test
    void testDelete() {
        ResponseEntity response = ResponseEntity.ok("Product deleted");

        when(productService.delete(anyLong())).thenReturn(response);

        ResponseEntity<?> result = productController.delete(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Product deleted", result.getBody());
        verify(productService, times(1)).delete(1L);
    }

    @Test
    void testUpdateStockNotFound() {
        Long productId = 99L;
        ProductStockDto stockDto = new ProductStockDto(10);

        when(productService.updateStock(productId, stockDto.getStock()))
                .thenAnswer(invocation ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found"));

        ResponseEntity<?> result = productController.updateStock(productId, stockDto);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals("Product not found", result.getBody());
    }


    @Test
    void testFindMaxStockByFranchise() {
        TopProductDTO top1 = new TopProductDTO(1L, "Sucursal Norte", 10L, "Café", 50);
        TopProductDTO top2 = new TopProductDTO(1L, "Sucursal Norte", 11L, "Té", 30);

        List<TopProductDTO> mockList = Arrays.asList(top1, top2);
        when(productService.findMaxStockByFranchise(anyLong())).thenReturn(mockList);

        ResponseEntity<?> result = productController.findMaxStockByFranchise(1L);

        @SuppressWarnings("unchecked")
        List<TopProductDTO> resultList = (List<TopProductDTO>) result.getBody();

        assertEquals(2, resultList.size());
        assertEquals("Café", resultList.get(0).productName());
        assertEquals(50, resultList.get(0).stock());
        assertEquals("Té", resultList.get(1).productName());
        assertEquals(30, resultList.get(1).stock());

        verify(productService, times(1)).findMaxStockByFranchise(1L);
    }

}
