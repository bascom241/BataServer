package com.example.bata.dtos.cartItem;

import com.example.bata.dtos.product.ImageDto;
import com.example.bata.model.ProductImages;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItemResponseDto {

    private Long cartItemId;
    private int quantity;
    private ProductImages productImage;
    private String productDescription;
    private String productName;
    private double productPrice;



}
