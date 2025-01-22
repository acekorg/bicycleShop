package com.aleksandar.core.model.base;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Model for invalid part combinations.
 * Needed to exclude or mark as invalid such options on the frontend.
 */
@Getter
@Setter
public class PartCombinationsInvalidityModel {

    private Long id;
    private Long firstPartId;
    private Long secondPartId;
    private Long productId;
    private BigDecimal surcharge;
}


