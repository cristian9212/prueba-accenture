package com.prueba.franquicias.repository;

import com.prueba.franquicias.dto.response.TopProductDTO;
import com.prueba.franquicias.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByBranchId(Long id);


    @Query("""
       select new com.prueba.franquicias.dto.response.TopProductDTO(
           b.id, b.name, p.id, p.name, p.stock
       )
       from ProductEntity p join p.branch b
       where b.franchise.id = :franchiseId
         and not exists (
           select 1 from ProductEntity p2
           where p2.branch = b
             and (p2.stock > p.stock or (p2.stock = p.stock and p2.id < p.id))
         )
       """)
    List<TopProductDTO> findTopPerBranchJpql(@Param("franchiseId") Long franchiseId);
}
