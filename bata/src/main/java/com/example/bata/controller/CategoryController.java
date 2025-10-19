package com.example.bata.controller;


import com.example.bata.dtos.product.CategoryDto;
import com.example.bata.dtos.product.ProductRequestDto;
import com.example.bata.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addCategory(
            @RequestPart("category")CategoryDto categoryDto,
            @RequestPart(value = "categoryImage", required = false)MultipartFile multipartFile
            ) throws IOException {
        categoryService.addCategory(categoryDto, multipartFile);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product Category Created");
    }

}
