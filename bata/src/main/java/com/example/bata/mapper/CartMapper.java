package com.example.bata.mapper;

import com.example.bata.dtos.cart.CartResponseDto;
import com.example.bata.dtos.cartItem.CartItemResponseDto;
import com.example.bata.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    @Autowired
    private CartItemMapper cartItemMapper;


    public CartResponseDto cartResponseDto (Cart cart){

        List<CartItemResponseDto> cartItemResponseDtos = cart.getCartItems()
                .stream().map(cartItemMapper::cartItemResponseDto).toList();
        return new CartResponseDto(
                cart.getCartId(),
                cartItemResponseDtos
        );
    }
}
