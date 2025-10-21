package com.example.bata.controller;

import com.example.bata.dtos.cart.CartResponseDto;
import com.example.bata.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")

public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add/{productId}/{quantity}")
    public ResponseEntity<?> addToCart(Authentication authentication,@PathVariable Long productId, @PathVariable int quantity) {
       String email = authentication.getName();
       if(email == null){
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("email is not found");
       }
        CartResponseDto cartResponseDto = cartService.addToCart(email, productId, quantity);
        return ResponseEntity.status(HttpStatus.OK).body(cartResponseDto);
    }


    @GetMapping("/get-cart")
    public ResponseEntity<?> getCartLists(Authentication authentication){
        String email = authentication.getName();
        if(email == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("email is not found");
        }
        CartResponseDto cartResponseDto = cartService.fetchCartProduct(email);
        
        return ResponseEntity.status(HttpStatus.OK).body(cartResponseDto);
    }



}
