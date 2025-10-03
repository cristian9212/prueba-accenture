package com.prueba.franquicias.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductMaxStockDto {
    private Long branchId;
    private String branchName;
    private Long productId;
    private String productName;
    private int stock;
}

