package com.prueba.franquicias.service.impl;

import com.prueba.franquicias.dto.FranchiseDto;
import com.prueba.franquicias.model.FranchiseEntity;
import com.prueba.franquicias.repository.FranchiseRepository;
import com.prueba.franquicias.service.FranchiseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static java.net.HttpURLConnection.HTTP_CREATED;

@Service
@AllArgsConstructor
public class FranchiseServiceImpl implements FranchiseService {

    private final FranchiseRepository franchiseRepository;


    @Override
    public ResponseEntity<?> createFranchise(FranchiseDto franchiseDto) {
        FranchiseEntity franchiseEntity = FranchiseEntity.builder()
                .name(franchiseDto.getName())
                .build();
        FranchiseEntity newFranchise =   franchiseRepository.save(franchiseEntity);
        return ResponseEntity.status(HTTP_CREATED).body(newFranchise);
    }
}
