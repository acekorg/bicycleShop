package com.aleksandar.core.model.admin;

import com.aleksandar.core.model.base.ProductModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
* Model that serves only for admin panel usage of Product data.
* Admin can see part price breakdown for different combinations
 */
@Getter
@Setter
public class ProductAdminModel extends ProductModel {

    private List<PartAdminModel> partAdminModelList;
}
