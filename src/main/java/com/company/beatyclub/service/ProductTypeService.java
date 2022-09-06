package com.company.beatyclub.service;

import com.company.beatyclub.model.ProductType;
import com.company.beatyclub.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ProductTypeService {
    @Autowired
    private ProductTypeRepository productTypeRepository;

    public ProductTypeService() {
    }

    public void createProductType(ProductType productType) {
        productTypeRepository.save(productType);
    }

    public ProductType getProductType(long id) {
        return productTypeRepository.findOne(id);
    }

    public void updateProductType(ProductType productType) {
        productTypeRepository.save(productType);
    }

    public void deleteProductType(Long id) {
        productTypeRepository.delete(id);
    }

    public List<ProductType> getAllProductTypes() {
        return productTypeRepository.findAll();
    }
}
