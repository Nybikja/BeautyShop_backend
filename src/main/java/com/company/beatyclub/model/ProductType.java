package com.company.beatyclub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import java.util.List;


@Table(name="product_type")
@Entity
@Data
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="searchValue")
    private String searchValue;
    @OneToMany(mappedBy = "productType")
    @JsonIgnore
    @ToString.Exclude
    private List<Product> products;
}
