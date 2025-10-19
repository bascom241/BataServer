package com.example.bata.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productCategoryId;
    private String productCategoryName;
    private String productCategoryImageName;
    private String productCategoryImageType;
    private String productCategoryImageUrl;

    @OneToMany(mappedBy = "productCategory", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();


}
