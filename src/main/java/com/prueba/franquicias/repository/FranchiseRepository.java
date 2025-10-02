package com.prueba.franquicias.repository;

import com.prueba.franquicias.model.FranchiseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseRepository extends JpaRepository <FranchiseEntity, Long> {



}
