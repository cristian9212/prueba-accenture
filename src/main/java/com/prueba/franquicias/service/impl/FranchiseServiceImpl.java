package com.prueba.franquicias.service.impl;

import com.prueba.franquicias.dto.FranchiseDto;
import com.prueba.franquicias.model.FranchiseEntity;
import com.prueba.franquicias.repository.FranchiseRepository;
import com.prueba.franquicias.service.FranchiseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;

@Service
@AllArgsConstructor
public class FranchiseServiceImpl implements FranchiseService {

    private final FranchiseRepository franchiseRepository;


    @Override
    public ResponseEntity<FranchiseDto> createFranchise(FranchiseDto franchiseDto) {

        franchiseRepository
                .findByName(franchiseDto.getName())
                .ifPresent(f -> {
                    throw new RuntimeException("Franquicia already exists");
                });

        FranchiseEntity franchiseEntity = FranchiseEntity.builder()
                .name(franchiseDto.getName())
                .build();

        FranchiseEntity newFranchise = franchiseRepository.save(franchiseEntity);

        FranchiseDto responseDto = new FranchiseDto();
        responseDto.setName(newFranchise.getName());


        return ResponseEntity.status(HTTP_CREATED).body(responseDto);
    }

    @Override
    public ResponseEntity<?> modifyFranchise(FranchiseDto franchiseDto, Long id) {
        FranchiseEntity franchiseEntity = franchiseRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Franquicia not found"));

        franchiseEntity.setName(franchiseDto.getName());
        FranchiseEntity modifiedFranchise = franchiseRepository.save(franchiseEntity);

        FranchiseDto dto = new FranchiseDto();
        dto.setName(modifiedFranchise.getName());

        return ResponseEntity.ok(dto);

}

    @Override
    public ResponseEntity<?> getAll() {
        var franchises = franchiseRepository.findAll();
        return ResponseEntity.status(HTTP_OK).body(franchises);
    }
}
