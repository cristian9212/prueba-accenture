package com.prueba.franquicias.controller.doc;

import com.prueba.franquicias.dto.FranchiseDto;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


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
}
