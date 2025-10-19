package com.example.bata.repository;


import com.example.bata.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<ProductCategory , Long> {
}
