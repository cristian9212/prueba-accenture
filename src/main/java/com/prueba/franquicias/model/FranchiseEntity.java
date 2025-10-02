package com.prueba.franquicias.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "franchise",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_franchise_name", columnNames = "name")
        }
)
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FranchiseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @OneToMany(
            mappedBy = "franchise",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
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

