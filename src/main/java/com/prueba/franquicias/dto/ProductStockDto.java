package com.prueba.franquicias.dto;


import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockDto {

    @Min(value = 1, message = "Stock must be at least 1")
    private int stock;
}

