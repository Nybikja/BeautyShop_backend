package com.company.beatyclub.repository;

import com.company.beatyclub.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    @Query("SELECT m FROM Product m WHERE m.hairType.name = :name")
    Page<Product> findProductsByHairTypeName(@Param("name") String name, Pageable pageable);

    @Query("SELECT m FROM Product m WHERE m.productType.name = :name")
    Page<Product> findProductsByProductTypeName(@Param("name") String name, Pageable pageable);

    @Query("SELECT m FROM Product m WHERE m.productLine.name = :name")
    Page<Product> findProductsByProductLineName(@Param("name") String name, Pageable pageable);
}
