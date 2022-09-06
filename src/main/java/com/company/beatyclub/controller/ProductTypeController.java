package com.company.beatyclub.controller;

import com.company.beatyclub.model.ProductType;
import com.company.beatyclub.service.ProductService;
import com.company.beatyclub.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value = "/productTypes")
@CrossOrigin(origins = "*")
public class ProductTypeController {
    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProductService productService;

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public void createGoods(@RequestBody ProductType productType) {
        productTypeService.createProductType(productType);
    }

    @RequestMapping()
    @ResponseBody
    public List<ProductType> getAllProductTypes() {
        return productTypeService.getAllProductTypes();
    }

    @GetMapping(value = "/id/{id}")
    @ResponseBody
    public ProductType getProductTypeByProductId(@PathVariable("id") Long id) {
        return productService.getProduct(id).getProductType();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ProductType getProductType(@PathVariable("id") Long id) {
        return productTypeService.getProductType(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ADMIN')")
    public void updateProductType(@RequestBody ProductType productType) {
        productTypeService.updateProductType(productType);
    }

    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProductType(@PathVariable("id") Long id) {
        productTypeService.deleteProductType(id);
    }
}
