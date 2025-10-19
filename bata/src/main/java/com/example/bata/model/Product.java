package com.example.bata.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private String productDescription;
    private double productPrice;
    private boolean isProductInStock = true;
    private int productQuantityInStock = 1;
    // Todo (To be removed in production)
    private boolean isTopSelling;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "product-productRatings")
    private List<ProductRating> productRatings = new ArrayList<>();
    @ManyToOne
    private ProductCategory productCategory;
    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL)
    private List<ProductSizes> productSizes;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImages> productImages = new ArrayList<>();



}
