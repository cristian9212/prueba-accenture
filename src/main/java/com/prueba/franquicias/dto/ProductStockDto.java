package com.prueba.franquicias.dto;


import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockDto {

    @Min(value = 0, message = "Stock must be greater than or equal to 0")
    private int stock;
}

