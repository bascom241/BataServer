package com.example.bata.controller;

import com.example.bata.dtos.product.ProductRequestDto;
import com.example.bata.dtos.product.ProductResponseDto;
import com.example.bata.dtos.user.UserRequestDto;
import com.example.bata.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/product")


public class ProductController {


    @Autowired
    private ProductService productService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addProduct(
            @RequestPart( "product") ProductRequestDto productRequestDto,
            @RequestPart(value = "images") List<MultipartFile> productImages
    ) throws IOException {
        productService.addProduct(productRequestDto,productImages);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product Created Successfully");
    }

    @GetMapping("/top-selling")
    public ResponseEntity<?> getTopSellingProducts(){
        List<ProductResponseDto> topSellingProducts = productService.getTopSellingProducts();
        return ResponseEntity.status(HttpStatus.OK).body(topSellingProducts);
    }

    @GetMapping("/single-product/{productId}")
    public ResponseEntity<?> getSingleProduct(@PathVariable Long productId){
        ProductResponseDto singleProduct = productService.getSingleProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body(singleProduct);

    }

}
