package com.aleksandar.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * PartCombinationsPricing entity.
 * Spring data matches Java variables to database columns when they are the same.
 */
@Entity
@Getter
@Setter
public class PartCombinationsPricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long mainPartId;
    private Long pairedPartId;
    private Long productId;
    private BigDecimal surcharge;
}
