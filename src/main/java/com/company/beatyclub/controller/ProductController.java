package com.company.beatyclub.controller;

import com.company.beatyclub.controller.dto.ProductConverter;
import com.company.beatyclub.model.Product;
import com.company.beatyclub.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public void createProduct(@RequestBody ProductConverter product) {
        productService.createProduct(product);
    }

    @RequestMapping()
    @ResponseBody
    public Page<Product> getAllProducts(Pageable pageable) {
        return productService.findAll(pageable);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Product getProduct(@PathVariable("id") Long id) {
        return productService.getProduct(id);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ADMIN')")
    public void updateProduct(@RequestBody ProductConverter product, @PathVariable("id") long id) {
        productService.updateProduct(product, id);
    }

    @GetMapping(value = "/searchHairType")
    public Page<Product> findProductsByHairTypeName(@RequestParam String name, Pageable pageable) {
        return productService.findProductsByHairTypeName(name, pageable);
    }

    @GetMapping(value = "/searchProductType")
    public Page<Product> findProductsByProductTypeName(@RequestParam String name, Pageable pageable) {
        return productService.findProductsByProductTypeName(name, pageable);
    }

    @GetMapping(value = "/searchProductLine")
    public Page<Product> findProductsByProductLineName(@RequestParam String name, Pageable pageable) {
        return productService.findProductsByProductLineName(name, pageable);
    }

    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }
}
