package com.company.beatyclub.repository;

import com.company.beatyclub.model.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductLineRepository extends JpaRepository<ProductLine, Long> {
    ProductLine findByName(String name);
}
