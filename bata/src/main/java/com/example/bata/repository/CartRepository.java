package com.example.bata.repository;

import com.example.bata.model.Cart;
import com.example.bata.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart , Long> {


    Optional<Cart> findByUser(User user);


}
