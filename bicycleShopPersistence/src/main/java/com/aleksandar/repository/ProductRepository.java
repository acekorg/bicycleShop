package com.aleksandar.repository;

import com.aleksandar.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Product repository.
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

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



}
