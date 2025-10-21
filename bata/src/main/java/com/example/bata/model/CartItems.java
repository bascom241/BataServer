package com.example.bata.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "cart-items")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;
    private int quantity;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Cart cart;
}
