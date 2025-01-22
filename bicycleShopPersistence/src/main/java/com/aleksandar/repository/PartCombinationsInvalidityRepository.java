package com.aleksandar.repository;

import com.aleksandar.entity.PartCombinationsInvalidity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Part combinations invalidity repository.
 */
@Repository
public interface PartCombinationsInvalidityRepository extends CrudRepository<PartCombinationsInvalidity, Long> {

    /**
     * Find all invalid combinations for certain product for validation purposes.
     * @param productId Product ID
     * @return Invalid part combinations
     */
    @Query(value = "SELECT * FROM PART_COMBINATIONS_INVALIDITY p WHERE p.productId IN :partIds", nativeQuery = true)
    List<PartCombinationsInvalidity> findInvalidCombinationsForProduct(Long productId);
}
