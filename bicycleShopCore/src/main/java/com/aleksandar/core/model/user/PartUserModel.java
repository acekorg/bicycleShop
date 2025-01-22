package com.aleksandar.core.model.user;

import com.aleksandar.core.model.base.PartModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Model that serves only for user UI usage of Part data.
 * This is for cases where price can be summed up.
 */
@Getter
@Setter
@NoArgsConstructor
public class PartUserModel extends PartModel {

    private BigDecimal price;
}
