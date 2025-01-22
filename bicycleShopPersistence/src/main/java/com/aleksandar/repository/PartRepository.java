package com.aleksandar.repository;

import com.aleksandar.entity.Part;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Part repository.
 */
@Repository
public interface PartRepository extends CrudRepository<Part, Long> {

    /**
     * Find specific parts with all their data.
     * @param partIds Part IDs
     * @return
     */
    @Query(value = "SELECT * FROM PART p WHERE p.id IN :partIds", nativeQuery = true)
    List<Part> findSelectedParts(List<Long> partIds);
}
