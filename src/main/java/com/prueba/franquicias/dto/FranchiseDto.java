package com.prueba.franquicias.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor

public class FranchiseDto {

    @NotBlank
    private String name;
}
