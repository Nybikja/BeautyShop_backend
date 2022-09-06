package com.company.beatyclub.service;

import com.company.beatyclub.controller.dto.ProductConverter;
import com.company.beatyclub.model.HairType;
import com.company.beatyclub.model.Product;
import com.company.beatyclub.model.ProductLine;
import com.company.beatyclub.model.ProductType;
import com.company.beatyclub.repository.HairTypeRepository;
import com.company.beatyclub.repository.ProductLineRepository;
import com.company.beatyclub.repository.ProductRepository;
import com.company.beatyclub.repository.ProductTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private ProductLineRepository productLineRepository;

    @Autowired
    private HairTypeRepository hairTypeRepository;

    public ProductService() {
    }

    private Product createProductFromConverter(ProductConverter product) {
        HairType hairTypeObject = hairTypeRepository.findByName(product.getHairType());
        ProductType productTypeObject = productTypeRepository.findByName(product.getProductType());
        ProductLine productLineObject = productLineRepository.findByName(product.getProductLine());

        return new Product(product.getName(), product.getDescription(), product.getPrice(),
                product.getImageUrl(), productTypeObject, hairTypeObject,
                productLineObject);
    }
    public void createProduct(ProductConverter product) {
        productRepository.save(createProductFromConverter(product));
    }

    public Product getProduct(long id) {
        return productRepository.findOne(id);
    }

    public void updateProduct(ProductConverter product, long id) {
        Product newProduct = productRepository.findOne(id);

        BeanUtils.copyProperties(createProductFromConverter(product), newProduct);
        newProduct.setId(id);
        productRepository.save(newProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.delete(id);
    }

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> findProductsByHairTypeName(String name, Pageable pageable) {
        return productRepository.findProductsByHairTypeName(name, pageable);
    }

    public Page<Product> findProductsByProductTypeName(String name, Pageable pageable) {
        return productRepository.findProductsByProductTypeName(name, pageable);
    }

    public Page<Product> findProductsByProductLineName(String name, Pageable pageable) {
        return productRepository.findProductsByProductLineName(name, pageable);
    }

}
