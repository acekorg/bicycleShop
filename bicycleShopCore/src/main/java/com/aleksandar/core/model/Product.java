package com.aleksandar.core.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private String name;
    private String description;
    private double price;
    private int stock;
}
