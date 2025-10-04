package com.prueba.franquicias.service;

import com.prueba.franquicias.dto.FranchiseDto;
import org.springframework.http.ResponseEntity;

public interface FranchiseService {

    ResponseEntity <FranchiseDto> createFranchise(FranchiseDto franchiseDto);

    ResponseEntity<?> modifyFranchise(FranchiseDto franchiseDto, Long id);

    ResponseEntity<?> getAll();
}
