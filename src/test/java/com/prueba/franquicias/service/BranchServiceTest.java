package com.prueba.franquicias.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.*;


import com.prueba.franquicias.dto.BranchDto;
import com.prueba.franquicias.model.BranchEntity;
import com.prueba.franquicias.model.FranchiseEntity;
import com.prueba.franquicias.repository.BranchRepository;
import com.prueba.franquicias.repository.FranchiseRepository;
import com.prueba.franquicias.service.impl.BranchServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.List;

class BranchServiceTest {

    @Mock
    private BranchRepository branchRepository;

    @Mock
    private FranchiseRepository franchiseRepository;

    @InjectMocks
    private BranchServiceImpl branchService;

    private BranchDto branchDto;
    private FranchiseEntity franchise;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        franchise = new FranchiseEntity();
        franchise.setId(1L);
        franchise.setName("Franquicia Test");

        branchDto = new BranchDto();
        branchDto.setName("Sucursal Test");
        branchDto.setFranchiseId(1L);
    }

    @Test
    void testCreateBranch_ok() {
        when(franchiseRepository.findById(1L)).thenReturn(Optional.of(franchise));
        when(branchRepository.findByNameAndFranchiseId("Sucursal Test", 1L)).thenReturn(Optional.empty());

        BranchEntity savedEntity = new BranchEntity();
        savedEntity.setId(1L);
        savedEntity.setName("Sucursal Test");
        savedEntity.setFranchise(franchise);

        when(branchRepository.save(any(BranchEntity.class))).thenReturn(savedEntity);

        // Ejecutar
        ResponseEntity<BranchDto> response = branchService.createBranch(branchDto);

        // Verificar
        assertEquals(CREATED, response.getStatusCode());
        assertEquals("Sucursal Test", response.getBody().getName());
        verify(branchRepository, times(1)).save(any(BranchEntity.class));
    }

    @Test
    void testCreateBranch_franchiseNotFound() {
        when(franchiseRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> branchService.createBranch(branchDto));

        assertTrue(exception.getMessage().contains("Franchise not found"));
    }

    @Test
    void testGetAll() {
        BranchEntity branch1 = new BranchEntity();
        branch1.setId(1L);
        branch1.setName("Sucursal 1");
        branch1.setFranchise(franchise);

        BranchEntity branch2 = new BranchEntity();
        branch2.setId(2L);
        branch2.setName("Sucursal 2");
        branch2.setFranchise(franchise);

        when(branchRepository.findAll()).thenReturn(List.of(branch1, branch2));

        ResponseEntity<?> response = branchService.getAll();

        assertEquals(OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof List);
        List<?> result = (List<?>) response.getBody();
        assertEquals(2, result.size());
    }
}
