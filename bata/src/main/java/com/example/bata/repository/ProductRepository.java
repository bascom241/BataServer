package com.example.bata.repository;

import com.example.bata.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product , Long> {
}
