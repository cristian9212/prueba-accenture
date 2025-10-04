package com.prueba.franquicias.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.franquicias.dto.BranchDto;
import com.prueba.franquicias.service.BranchService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BranchController.class)
class BranchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BranchService branchService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateBranch() throws Exception {
        BranchDto dto = new BranchDto("Sucursal Centro", 1L);

        Mockito.when(branchService.createBranch(Mockito.any(BranchDto.class)))
                .thenReturn(ResponseEntity.ok().body(dto));


        mockMvc.perform(post("/branches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sucursal Centro"))
                .andExpect(jsonPath("$.franchiseId").value(1));
    }


    @Test
    void testModifyBranch() throws Exception {
        BranchDto dto = new BranchDto("Sucursal Norte", 2L);

        ResponseEntity<?> response = ResponseEntity.ok(dto);

        Mockito.when(branchService.createBranch(Mockito.any(BranchDto.class)))
                .thenReturn(ResponseEntity.ok().body(dto));


        mockMvc.perform(put("/branches/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sucursal Norte"))
                .andExpect(jsonPath("$.franchiseId").value(2));
    }

    @Test
    void testGetAllBranches() throws Exception {
        BranchDto dto = new BranchDto("Sucursal Sur", 3L);

        ResponseEntity<?> response = ResponseEntity.ok(List.of(dto));

        Mockito.when(branchService.createBranch(Mockito.any(BranchDto.class)))
                .thenReturn(ResponseEntity.ok().body(dto));


        mockMvc.perform(get("/branches"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Sucursal Sur"))
                .andExpect(jsonPath("$[0].franchiseId").value(3));
    }

}


