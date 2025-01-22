package com.aleksandar.core.model.base;

import com.aleksandar.entity.PartType;
import lombok.Getter;
import lombok.Setter;

/**
 * Base part model with data that's omnipresent.
 * Doesn't have to be abstract if there is need in the future to use it in this form.
 */
@Getter
@Setter
public abstract class PartModel {

    private Long id;
    private PartType partType;
    private String name;
    private int stock;
}
