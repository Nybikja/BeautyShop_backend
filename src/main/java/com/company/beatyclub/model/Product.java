package com.company.beatyclub.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Entity
@Table(name="product")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private int price;

    @Column(name="image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "hair_type_id")
    private HairType hairType;

    @ManyToOne
    @JoinColumn(name = "product_line_id")
    private ProductLine productLine;

    public Product(String name, String description, int price, String imageUrl, ProductType productType,
                   HairType hairType, ProductLine productLine) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.productType = productType;
        this.hairType = hairType;
        this.productLine = productLine;
    }
}
