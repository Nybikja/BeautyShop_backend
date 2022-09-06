package com.company.beatyclub.controller.dto;

import lombok.Data;


@Data
public class ProductConverter {
    private String name;

    private String description;

    private int price;

    private String imageUrl;

    private String productType;

    private String hairType;

    private String productLine;
}
