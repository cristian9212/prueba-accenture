package com.prueba.franquicias.controller;

import com.prueba.franquicias.controller.doc.BranchDoc;
import com.prueba.franquicias.dto.BranchDto;
import com.prueba.franquicias.service.BranchService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BranchController implements BranchDoc {

    private final BranchService branchService;

    @Override
    public ResponseEntity<?> create(BranchDto branchDto) {
        return branchService.createBranch(branchDto);
    }

    @Override
    public ResponseEntity<?> modify(BranchDto branchDto, Long id) {
        return branchService.modifyBranch(branchDto, id);
    }

    @Override
    public ResponseEntity<?> getAll() {
        return branchService.getAll();
    }
}
