package com.example.bata.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_images")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productImageId;
    private String productImageType;
    private String productImageName;
    private String productImageUrl;

    @ManyToOne
    private Product product;

}
