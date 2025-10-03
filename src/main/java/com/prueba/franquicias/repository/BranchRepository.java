package com.prueba.franquicias.repository;

import com.prueba.franquicias.model.BranchEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BranchRepository extends JpaRepository<BranchEntity, Long> {
    List<BranchEntity> findByFranchiseId(Long franchiseId);

    Optional<Object> findByNameAndFranchiseId(@NotBlank String name, @NotNull @Positive Long franchiseId);

}
