package com.example.bata.mapper;

import com.example.bata.dtos.cartItem.CartItemResponseDto;
import com.example.bata.model.CartItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {

    public CartItemResponseDto cartItemResponseDto(CartItems cartItems){
        return new CartItemResponseDto(
                cartItems.getCartItemId(),
                cartItems.getQuantity(),
                cartItems.getProduct().getProductImages().get(0),
                cartItems.getProduct().getProductDescription(),
                cartItems.getProduct().getProductName(),
                cartItems.getProduct().getProductPrice()
                );
    }
}
