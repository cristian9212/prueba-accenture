package com.prueba.franquicias.dto.response;

public record TopProductDTO(
        Long branchId,
        String branchName,
        Long productId,
        String productName,
        Integer stock
) {}
