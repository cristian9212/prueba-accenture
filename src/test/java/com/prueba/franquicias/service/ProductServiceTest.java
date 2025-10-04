package com.prueba.franquicias.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.*;

import com.prueba.franquicias.dto.ProductDto;
import com.prueba.franquicias.dto.response.TopProductDTO;

import com.prueba.franquicias.model.BranchEntity;
import com.prueba.franquicias.model.ProductEntity;
import com.prueba.franquicias.repository.BranchRepository;
import com.prueba.franquicias.repository.ProductRepository;
import com.prueba.franquicias.service.impl.ProductServiceImpl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private BranchRepository branchRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private ProductDto productDto;
    private ProductEntity productEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        productDto = new ProductDto();
        productDto.setName("Producto Test");
        productDto.setStock(10);

        productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setName("Producto Test");
        productEntity.setStock(10);
    }

    @Test
    void testCreateProduct_ok() {
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);

        ResponseEntity<?> response = productService.create(productDto);

        assertEquals(CREATED, response.getStatusCode());
        assertTrue(response.getBody() instanceof ProductDto);
        assertEquals("Producto Test", ((ProductDto) response.getBody()).getName());
        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void testDeleteProduct_ok() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));
        doNothing().when(productRepository).delete(productEntity);

        ResponseEntity<?> response = productService.delete(1L);

        assertEquals(NO_CONTENT, response.getStatusCode());
        verify(productRepository, times(1)).delete(productEntity);
    }

    @Test
    void testDeleteProduct_notFound() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> productService.delete(99L));

        assertTrue(exception.getMessage().contains("not found"));
    }

    @Test
    void testUpdateStock_ok() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);

        ResponseEntity<?> response = productService.updateStock(1L, 20);

        assertEquals(OK, response.getStatusCode());
        assertEquals(20, ((ProductDto) response.getBody()).getStock());
    }

    @Test
    void testUpdateStock_notFound() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> productService.updateStock(99L, 50));

        assertTrue(exception.getMessage().contains("not found"));
    }

    @Test
    void testFindMaxStockByFranchise() {
        Long franchiseId = 1L;
        TopProductDTO top1 = new TopProductDTO(1L, "Prod A", 50L, "Product A", 50);
        TopProductDTO top2 = new TopProductDTO(2L, "Prod B", 40L, "Product B", 40);


        BranchEntity mockBranch = new BranchEntity();
        mockBranch.setId(franchiseId);
        when(branchRepository.findById(franchiseId))
                .thenReturn(Optional.of(mockBranch));


        when(productRepository.findTopPerBranchJpql(franchiseId))
                .thenReturn(List.of(top1, top2));


        List<TopProductDTO> result = productService.findMaxStockByFranchise(franchiseId);


        assertEquals(2, result.size());
        assertEquals("Prod A", result.get(0).branchName());
        verify(branchRepository, times(1)).findById(franchiseId);
        verify(productRepository, times(1)).findTopPerBranchJpql(franchiseId);
    }
}
