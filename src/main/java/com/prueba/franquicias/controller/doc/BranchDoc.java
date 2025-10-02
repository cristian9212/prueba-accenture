package com.prueba.franquicias.controller.doc;


import com.prueba.franquicias.dto.BranchDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Branches", description = "Branches API")
@RequestMapping("/branches")
public interface BranchDoc {
    @Operation(summary = "Create Branches", description = "Create a new branches")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Branches created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    ResponseEntity<?> create (@Valid @RequestBody BranchDto branchDto);

    @Operation(summary = "Modify Branches", description = "Modify an existing branch")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Branch modified successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Branch not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    ResponseEntity<?> modify(@Valid @RequestBody BranchDto branchDto,
                             @PathVariable Long id);

    @GetMapping
    ResponseEntity<?> getAll();

}
