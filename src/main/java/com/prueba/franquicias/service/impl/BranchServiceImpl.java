package com.prueba.franquicias.service.impl;

import com.prueba.franquicias.dto.BranchDto;
import com.prueba.franquicias.model.BranchEntity;
import com.prueba.franquicias.model.FranchiseEntity;
import com.prueba.franquicias.repository.BranchRepository;
import com.prueba.franquicias.repository.FranchiseRepository;
import com.prueba.franquicias.service.BranchService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final FranchiseRepository franchiseRepository;

    @Override
    public ResponseEntity<BranchDto> createBranch(BranchDto branchDto) {

        FranchiseEntity franchise = franchiseRepository
                .findById(branchDto.getFranchiseId())
                .orElseThrow(() -> new RuntimeException("Franchise not found with id: " + branchDto.getFranchiseId()));

        branchRepository.findByNameAndFranchiseId(branchDto.getName(), branchDto.getFranchiseId())
                .ifPresent(b -> {
                    throw new RuntimeException("Branch with name " + branchDto.getName() + " already exists for this franchise.");
                });

        BranchEntity branchEntity = BranchEntity.builder()
                .name(branchDto.getName())
                .franchise(franchise)
                .build();

        BranchEntity newBranch = branchRepository.save(branchEntity);

        BranchDto responseDto = new BranchDto(
                newBranch.getName(),
                newBranch.getFranchise().getId()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Override
    public ResponseEntity<?> modifyBranch(BranchDto branchDto, Long id) {
        BranchEntity branchEntity = branchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Branch not found with id: " + id));

        branchEntity.setName(branchDto.getName());

        if (branchDto.getFranchiseId() != null) {
            FranchiseEntity franchise = franchiseRepository.findById(branchDto.getFranchiseId())
                    .orElseThrow(() -> new RuntimeException("Franchise not found with id: " + branchDto.getFranchiseId()));
            branchEntity.setFranchise(franchise);
        }

        BranchEntity modifiedBranch = branchRepository.save(branchEntity);
        return ResponseEntity.ok(modifiedBranch);
    }

    @Override
    public ResponseEntity<?> getAll() {
        var branches = branchRepository.findAll();
        return ResponseEntity.ok(branches);
    }
}


