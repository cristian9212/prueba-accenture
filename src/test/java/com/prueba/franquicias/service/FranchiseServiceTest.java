package com.prueba.franquicias.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.*;

import com.prueba.franquicias.dto.FranchiseDto;
import com.prueba.franquicias.model.FranchiseEntity;
import com.prueba.franquicias.repository.FranchiseRepository;
import com.prueba.franquicias.service.impl.FranchiseServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.List;

class FranchiseServiceTest {

    @Mock
    private FranchiseRepository franchiseRepository;

    @InjectMocks
    private FranchiseServiceImpl franchiseService;

    private FranchiseDto franchiseDto;
    private FranchiseEntity franchiseEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        franchiseDto = new FranchiseDto();
        franchiseDto.setName("Franquicia Test");

        franchiseEntity = new FranchiseEntity();
        franchiseEntity.setId(1L);
        franchiseEntity.setName("Franquicia Test");
    }

    @Test
    void testCreateFranchise_ok() {
        when(franchiseRepository.findByName("Franquicia Test")).thenReturn(Optional.empty());
        when(franchiseRepository.save(any(FranchiseEntity.class))).thenReturn(franchiseEntity);

        ResponseEntity<FranchiseDto> response = franchiseService.createFranchise(franchiseDto);

        assertEquals(CREATED, response.getStatusCode());
        assertEquals("Franquicia Test", response.getBody().getName());
        verify(franchiseRepository, times(1)).save(any(FranchiseEntity.class));
    }

    @Test
    void testCreateFranchise_duplicateName() {
        when(franchiseRepository.findByName("Franquicia Test")).thenReturn(Optional.of(franchiseEntity));

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> franchiseService.createFranchise(franchiseDto));

        assertTrue(exception.getMessage().contains("already exists"));
    }

    @Test
    void testModifyFranchise_ok() {
        when(franchiseRepository.findById(1L)).thenReturn(Optional.of(franchiseEntity));
        when(franchiseRepository.save(any(FranchiseEntity.class))).thenReturn(franchiseEntity);

        franchiseDto.setName("Franquicia Modificada");
        franchiseEntity.setName("Franquicia Modificada");

        ResponseEntity<?> response = franchiseService.modifyFranchise(franchiseDto, 1L);

        assertEquals(OK, response.getStatusCode());
        assertEquals("Franquicia Modificada", ((FranchiseDto) response.getBody()).getName());
    }

    @Test
    void testModifyFranchise_notFound() {
        when(franchiseRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> franchiseService.modifyFranchise(franchiseDto, 99L));

        assertTrue(exception.getMessage().contains("not found"));
    }

    @Test
    void testGetAll() {
        FranchiseEntity f1 = new FranchiseEntity();
        f1.setId(1L);
        f1.setName("Franquicia 1");

        FranchiseEntity f2 = new FranchiseEntity();
        f2.setId(2L);
        f2.setName("Franquicia 2");

        when(franchiseRepository.findAll()).thenReturn(List.of(f1, f2));

        ResponseEntity<?> response = franchiseService.getAll();

        assertEquals(OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof List);
        List<?> result = (List<?>) response.getBody();
        assertEquals(2, result.size());
    }
}
