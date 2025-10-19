package com.example.bata.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_sizes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductSizes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productSizeId;
    private String sizeLabel; // e.g. "Small", "Medium", "Large"
    private int quantityAvailable;
    @ManyToOne
    private Product product;
}
