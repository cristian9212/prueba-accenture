package com.prueba.franquicias.controller;



import com.prueba.franquicias.dto.FranchiseDto;
import com.prueba.franquicias.service.FranchiseService;
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
import static org.mockito.Mockito.*;

class FranchiseControllerTest {

    @Mock
    private FranchiseService franchiseService;

    @InjectMocks
    private FranchiseController franchiseController;

    private FranchiseDto franchiseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        franchiseDto = new FranchiseDto();
        franchiseDto.setName("Crepes & Waffles");
    }

    @Test
    void testCreate() {
        FranchiseDto createdDto = new FranchiseDto();
        createdDto.setName("Crepes & Waffles");

        ResponseEntity<FranchiseDto> response = ResponseEntity.status(HttpStatus.CREATED).body(createdDto);

        when(franchiseService.createFranchise(any(FranchiseDto.class))).thenReturn(response);

        ResponseEntity<?> result = franchiseController.create(new FranchiseDto());

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("Crepes & Waffles", ((FranchiseDto) result.getBody()).getName());
        verify(franchiseService, times(1)).createFranchise(any(FranchiseDto.class));
    }

    @Test
    void testModify() {
        FranchiseDto modifiedDto = new FranchiseDto();
        modifiedDto.setName("Franchise modified");

        ResponseEntity response = ResponseEntity.ok(modifiedDto);
        when(franchiseService.modifyFranchise(any(FranchiseDto.class), anyLong())).thenReturn(response);


        ResponseEntity<?> result = franchiseController.modify(new FranchiseDto(), 1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Franchise modified", ((FranchiseDto) result.getBody()).getName());
        verify(franchiseService, times(1)).modifyFranchise(any(FranchiseDto.class), anyLong());
    }

    @Test
    void testGetAll() {
        FranchiseDto dto1 = new FranchiseDto();
        dto1.setName("Crepes & Waffles");
        FranchiseDto dto2 = new FranchiseDto();
        dto2.setName("Juan Valdez Café");

        List<FranchiseDto> list = Arrays.asList(dto1, dto2);

        // Raw type para evitar problemas de genéricos
        ResponseEntity response = ResponseEntity.ok(list);

        // Mock del servicio
        when(franchiseService.getAll()).thenReturn(response);

        // Llamada al controlador
        ResponseEntity<?> result = franchiseController.getAll();

        // Verificaciones
        assertEquals(HttpStatus.OK, result.getStatusCode());
        List<FranchiseDto> resultList = (List<FranchiseDto>) result.getBody();
        assertEquals(2, resultList.size());
        assertEquals("Crepes & Waffles", resultList.get(0).getName());
        assertEquals("Juan Valdez Café", resultList.get(1).getName());

        verify(franchiseService, times(1)).getAll();
    }

}


