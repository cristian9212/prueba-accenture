package com.prueba.franquicias.controller;

import com.prueba.franquicias.controller.doc.FranchiseDoc;
import com.prueba.franquicias.dto.FranchiseDto;
import com.prueba.franquicias.service.FranchiseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class FranchiseController  implements FranchiseDoc {

    private final FranchiseService franchiseService;

    @Override
    public ResponseEntity<?> create(FranchiseDto franchiseDto) {
        return franchiseService.createFranchise(franchiseDto);

    }
}
