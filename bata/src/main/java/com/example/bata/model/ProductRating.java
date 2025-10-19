package com.example.bata.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_rating")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productRatingId;
    private int productRating;
    private String productComment;
    private String productRaterFirstName;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-productRatings")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference(value = "product-productRatings")
    private Product product;
}
