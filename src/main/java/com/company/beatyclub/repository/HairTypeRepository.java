package com.company.beatyclub.repository;

import com.company.beatyclub.model.HairType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HairTypeRepository extends JpaRepository<HairType, Long> {
    HairType findByName(String name);
}
