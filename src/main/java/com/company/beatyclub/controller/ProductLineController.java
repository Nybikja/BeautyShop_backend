package com.company.beatyclub.controller;

import com.company.beatyclub.model.ProductLine;
import com.company.beatyclub.service.ProductLineService;
import com.company.beatyclub.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value = "/productLines")
@CrossOrigin(origins = "*")
public class ProductLineController {
    @Autowired
    private ProductLineService productLineService;

    @Autowired
    private ProductService productService;

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public void createGoods(@RequestBody ProductLine productLine) {
        productLineService.createProductLine(productLine);
    }

    @RequestMapping()
    @ResponseBody
    public List<ProductLine> getAllProductLines() {
        System.out.println("productLines");
        return productLineService.getAllProductLines();
    }

    @GetMapping(value = "/id/{id}")
    @ResponseBody
    public ProductLine getProductLineByProductId(@PathVariable("id") Long id) {
        return productService.getProduct(id).getProductLine();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ProductLine getProductLine(@PathVariable("id") Long id) {
        return productLineService.getProductLine(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ADMIN')")
    public void updateProductLine(@PathVariable("id") Long id, @RequestBody ProductLine productLine) {
        productLineService.updateProductLine(productLine);
    }

    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProductLine(@PathVariable("id") Long id) {
        productLineService.deleteProductLine(id);
    }
}
