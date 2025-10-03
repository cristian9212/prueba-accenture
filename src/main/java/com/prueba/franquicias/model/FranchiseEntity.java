package com.prueba.franquicias.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "franchise",
        uniqueConstraints = @UniqueConstraint(name = "uk_franchise_name", columnNames = "name")
)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FranchiseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @NotBlank
    @Column(name = "name", nullable = false, length = 200)
    @ToString.Include
    private String name;

    @OneToMany(mappedBy = "franchise", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @JsonManagedReference("franchise-branches")
    private List<BranchEntity> branches = new ArrayList<>();

    public void addBranch(BranchEntity branch) {
        branches.add(branch);
        branch.setFranchise(this);
    }

    public void removeBranch(BranchEntity branch) {
        branches.remove(branch);
        branch.setFranchise(null);
    }
}

