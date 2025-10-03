package com.prueba.franquicias.repository;

import com.prueba.franquicias.model.BranchEntity;
import com.prueba.franquicias.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<BranchEntity> findByFranchiseId(Long franchiseId);
    List<ProductEntity> findByBranchId(Long id);
}
