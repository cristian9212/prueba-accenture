package com.prueba.franquicias.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "branch",
        uniqueConstraints = @UniqueConstraint(name = "uk_branch_franchise_name", columnNames = {"franchise_id","name"})
)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BranchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @NotBlank
    @Column(name = "name", nullable = false, length = 200)
    @ToString.Include
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "franchise_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_branch_franchise"))
    @JsonBackReference("franchise-branches")
    private FranchiseEntity franchise;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @JsonManagedReference("branch-products")
    private List<ProductEntity> products = new ArrayList<>();

    public void addProduct(ProductEntity product) {
        products.add(product);
        product.setBranch(this);
    }

    public void removeProduct(ProductEntity product) {
        products.remove(product);
        product.setBranch(null);
    }
}
