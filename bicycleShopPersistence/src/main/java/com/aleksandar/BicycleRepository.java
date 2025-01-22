package com.aleksandar;

import com.aleksandar.model.Part;
import com.aleksandar.model.PartCombinationsInvalidity;
import com.aleksandar.model.PartCombinationsPricing;
import com.aleksandar.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BicycleRepository extends CrudRepository<Product, Long> {

    /**
     * Get product with all possible parts for selection.
     * @param productId Product ID
     * @return Product with all its possible parts
     */
    @Query(value = "SELECT * FROM PRODUCT p LEFT JOIN PART pr ON p.id = pr.productId WHERE p.id = :productId",
            nativeQuery = true)
    Product findProductWithPartsById(Long productId);

    /**
     * Get list of all bicycles for sale.
     * @return All bicycles
     */
    @Query(value = "SELECT * FROM PRODUCT p WHERE productType = 'BICYCLE'", nativeQuery = true)
    List<Product> findAllBicycles();

    /**
     * Find specific parts with all their data.
     * @param partIds Part IDs
     * @return
     */
    @Query(value = "SELECT * FROM PART p WHERE p.id IN :partIds", nativeQuery = true)
    List<Part> findSelectedParts(List<Long> partIds);

    /**
     * Find all invalid combinations for certain product for validation purposes.
     * @param productId Product ID
     * @return Invalid part combinations
     */
    @Query(value = "SELECT * FROM PART_COMBINATIONS_INVALIDITY p WHERE p.productId IN :partIds", nativeQuery = true)
    List<PartCombinationsInvalidity> findInvalidCombinationsForProduct(Long productId);

    /**
     * Find surcharges for given parts combinations.
     * @param partIds Part IDs
     * @return List of part combinations surcharges
     */
    @Query(value = "SELECT * FROM PART_COMBINATIONS_PRICING p WHERE p.firstPartId IN :partIds AND p.secondPartId IN :partIds", nativeQuery = true)
    List<PartCombinationsPricing> findPartCombinationsSurcharges(List<Long> partIds);

}
