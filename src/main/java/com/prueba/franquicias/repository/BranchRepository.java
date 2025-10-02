package com.prueba.franquicias.repository;

import com.prueba.franquicias.model.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BranchRepository extends JpaRepository<BranchEntity, Long> {
    Optional<BranchEntity> findByNameAndFranchiseId(String name, Long franchiseId);
}
