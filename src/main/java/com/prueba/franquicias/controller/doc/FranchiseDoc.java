package com.prueba.franquicias.controller.doc;

import com.prueba.franquicias.dto.FranchiseDto;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Franchise", description = "Franchise API")
@RequestMapping("/franchise")
public interface FranchiseDoc  {

    @Operation(summary = "Create Franchise", description = "Create a new franchise")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Franchise created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    ResponseEntity<?> create (@Valid @RequestBody FranchiseDto franchiseDto);

    @Operation(summary = "Modify Franchise", description = "Modify an existing franchise")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Franchise modified successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "404", description = "Franchise not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    ResponseEntity<?> modify (@Valid @RequestBody  FranchiseDto franchiseDto, Long id);

    @Operation(summary = "Get All Franchises", description = "Retrieve all franchises")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Franchises retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    ResponseEntity<?> getAll();

}
