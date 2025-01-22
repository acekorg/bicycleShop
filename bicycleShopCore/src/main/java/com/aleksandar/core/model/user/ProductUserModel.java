package com.aleksandar.core.model.user;

import com.aleksandar.core.model.base.ProductModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Model that serves only for user UI usage of Product data.
 */
@Getter
@Setter
public class ProductUserModel extends ProductModel {

    private List<PartUserModel> partModelList;
}
