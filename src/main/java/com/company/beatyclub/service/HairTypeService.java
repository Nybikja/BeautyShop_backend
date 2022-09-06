package com.company.beatyclub.service;

import com.company.beatyclub.model.HairType;
import com.company.beatyclub.repository.HairTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class HairTypeService {
    @Autowired
    private HairTypeRepository hairTypeRepository;


    public HairTypeService() {
    }

    public void createHairType(HairType productType) {
        hairTypeRepository.save(productType);
    }

    public HairType getHairType(long id) {
        return hairTypeRepository.findOne(id);
    }

    public void updateHairType(HairType productType) {
        hairTypeRepository.save(productType);
    }

    public void deleteHairType(Long id) {
        hairTypeRepository.delete(id);
    }

    public List<HairType> getAllHairTypes() {
        return hairTypeRepository.findAll();
    }
}
