package com.prueba.franquicias.service;

import com.prueba.franquicias.dto.BranchDto;
import org.springframework.http.ResponseEntity;

public interface BranchService {

    ResponseEntity<?> createBranch(BranchDto branchDto);

    ResponseEntity<?> modifyBranch(BranchDto branchDto, Long id);

    ResponseEntity<?> getAll();
}
