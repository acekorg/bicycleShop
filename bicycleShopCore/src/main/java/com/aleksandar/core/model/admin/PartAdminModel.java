package com.aleksandar.core.model.admin;

import com.aleksandar.core.model.base.PartModel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Model that serves only for admin panel usage of Part data.
 * Admin can see base price and define surcharges for part combinations.
 */
@Getter
@Setter
public class PartAdminModel extends PartModel {

    BigDecimal basePrice;
}
