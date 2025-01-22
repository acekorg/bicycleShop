package com.aleksandar.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Product entity.
 * Spring data matches Java variables to database columns when they are the same.
 */
@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_type")
    private ProductType productType;
    private String description;
    private String brand;
    private String model;
    private int stock;

    @ManyToMany
    @JoinTable(
            name = "PRODUCT_PART",
            joinColumns = @JoinColumn(name = "productId"),
            inverseJoinColumns = @JoinColumn(name = "partId")
    )
    private List<Part> parts;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PartCombinationsInvalidity> partCombinationsInvalidities;

}
