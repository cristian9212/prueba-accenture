package com.prueba.franquicias.repository;

import com.prueba.franquicias.model.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BranchRepository extends JpaRepository<BranchEntity, Long> {
    List<BranchEntity> findByFranchiseId(Long franchiseId);
}
