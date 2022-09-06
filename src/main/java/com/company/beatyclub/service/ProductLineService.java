package com.company.beatyclub.service;

import com.company.beatyclub.model.ProductLine;
import com.company.beatyclub.repository.ProductLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class ProductLineService {

    @Autowired
    private ProductLineRepository productLineRepository;

    public ProductLineService() {
    }

    public void createProductLine(ProductLine productLine) {
        productLineRepository.save(productLine);
    }

    public ProductLine getProductLine(long id) {
        System.out.println(productLineRepository.findOne(id).getName());
        System.out.println("getPRoduct");
        return productLineRepository.findOne(id);
    }

    public void updateProductLine(ProductLine productLine) {
        productLineRepository.save(productLine);
    }

    public void deleteProductLine(Long id) {
        productLineRepository.delete(id);
    }

    public List<ProductLine> getAllProductLines() {
        return productLineRepository.findAll();
    }
}
