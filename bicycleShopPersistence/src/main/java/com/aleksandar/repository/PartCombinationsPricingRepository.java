package com.aleksandar.repository;

import com.aleksandar.entity.PartCombinationsPricing;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Part combinations pricing repository.
 */
@Repository
public interface PartCombinationsPricingRepository extends CrudRepository<PartCombinationsPricingRepository, Long> {

    /**
     * Find surcharges for given parts combinations.
     * @param partIds Part IDs
     * @return List of part combinations surcharges
     */
    @Query(value = "SELECT * FROM PART_COMBINATIONS_PRICING p WHERE p.mainPartId IN :partIds AND p.pairedPartId IN :partIds", nativeQuery = true)
    List<PartCombinationsPricing> findPartCombinationsSurcharges(List<Long> partIds);

}
