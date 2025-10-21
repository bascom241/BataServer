package com.example.bata.dtos.cart;

import com.example.bata.dtos.cartItem.CartItemResponseDto;
import com.example.bata.model.Cart;
import com.example.bata.model.CartItems;
import com.example.bata.model.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CartResponseDto {

    private Long cartId;
    private List<CartItemResponseDto> cartItemResponseDtos;
}
