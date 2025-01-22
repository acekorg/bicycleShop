package com.aleksandar.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

/**
 * PartCombinationsInvalidity entity.
 * Spring data matches Java variables to database columns when they are the same.
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartCombinationsInvalidity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long firstPartId;
    private Long secondPartId;
    private Long productId;

}
