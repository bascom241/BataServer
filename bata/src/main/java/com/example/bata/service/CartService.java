package com.example.bata.service;

import com.example.bata.dao.UserRepository;
import com.example.bata.dtos.cart.CartResponseDto;
import com.example.bata.mapper.CartItemMapper;
import com.example.bata.mapper.CartMapper;
import com.example.bata.model.Cart;
import com.example.bata.model.CartItems;
import com.example.bata.model.Product;
import com.example.bata.model.User;
import com.example.bata.repository.CartRepository;
import com.example.bata.repository.CartItemRepository;
import com.example.bata.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;



    @Autowired
    private CartMapper cartMapper;


    public User findEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("user not found "));
    }


    public CartResponseDto addToCart(String email, Long productId, int quantity){
        // find if the user exits

       User user = findEmail(email);
        // find if user has a cart else create for them

        Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepository.save(newCart);
        });

        // find if the product they want to add exist in the Database
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product does not exist"));

        // find if the product already exits in the cartitems  , if its exits increase quantity

        Optional <CartItems> existingItem = cart.getCartItems().stream().filter(cartItems -> Objects.equals(cartItems.getProduct().getProductId(), productId)).findFirst();



        if(existingItem.isPresent()){
            CartItems item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            cartItemRepository.save(item);
        }else {
           CartItems cartItems = new CartItems();
            cartItems.setQuantity(quantity);
            cartItems.setProduct(product);
            cartItems.setCart(cart);
            cart.getCartItems().add(cartItems);
            cartItemRepository.save(cartItems);

        }

       cartRepository.save(cart);
       return cartMapper.cartResponseDto(cart);

    }


    public CartResponseDto fetchCartProduct(String email){
        User user = findEmail(email);
        Cart cart = cartRepository.findByUser(user).orElseThrow(()-> new RuntimeException("Cart not found this user"));
        return cartMapper.cartResponseDto(cart);
    }


}
