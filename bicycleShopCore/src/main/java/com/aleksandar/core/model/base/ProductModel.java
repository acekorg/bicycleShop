package com.aleksandar.core.model.base;

import com.aleksandar.entity.ProductType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Base product model with data that's omnipresent.w
 * Doesn't have to be abstract if there is need in the future to use it in this form.
 */
@Getter
@Setter
public abstract class ProductModel {

    private Long id;
    private ProductType productType;
    private String description;
    private String brand;
    private String model;
    private int stock;
    private List<PartCombinationsInvalidityModel> partCombinationsInvalidityList;
}
