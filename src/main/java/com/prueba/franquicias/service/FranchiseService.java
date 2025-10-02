package com.prueba.franquicias.service;

import com.prueba.franquicias.dto.FranchiseDto;
import org.springframework.http.ResponseEntity;

public interface FranchiseService {

    ResponseEntity <?> createFranchise(FranchiseDto franchiseDto);
}
