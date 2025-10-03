package com.prueba.franquicias.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(
        name = "product",
        uniqueConstraints = @UniqueConstraint(name = "uk_product_branch_name", columnNames = {"branch_id","name"})
)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @NotBlank
    @Column(name = "name", nullable = false, length = 200)
    @ToString.Include
    private String name;

    @Min(0)
    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Version
    private Long version;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "branch_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_product_branch"))
    @JsonBackReference("branch-products") // <-- corta ciclo hacia Branch
    private BranchEntity branch;
}
