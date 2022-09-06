package com.company.beatyclub.controller;

import com.company.beatyclub.model.HairType;
import com.company.beatyclub.service.HairTypeService;
import com.company.beatyclub.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value = "/hairTypes")
@CrossOrigin(origins = "*")
public class HairTypeController {
    @Autowired
    private HairTypeService hairTypeService;

    @Autowired
    private ProductService productService;

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public void createHairType(@RequestBody HairType hairType) {
        hairTypeService.createHairType(hairType);
    }

    @RequestMapping()
    @ResponseBody
    public List<HairType> getAllHairTypes() {
        return hairTypeService.getAllHairTypes();
    }

    @GetMapping(value = "/id/{id}")
    @ResponseBody
    public HairType getHairTypeByProductId(@PathVariable("id") Long id) {
        return productService.getProduct(id).getHairType();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public HairType getHairType(@PathVariable("id") Long id) {
        return hairTypeService.getHairType(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ADMIN')")
    public void updateHairType(@RequestBody HairType hairType) {
        hairTypeService.updateHairType(hairType);
    }

    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteHairType(@PathVariable("id") Long id) {
        hairTypeService.deleteHairType(id);
    }
}
