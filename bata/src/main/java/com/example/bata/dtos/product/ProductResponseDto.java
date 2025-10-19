package com.example.bata.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductResponseDto {
    private Long productId;
    private String productName;
    private String productDescription;
    private double productPrice;
    private boolean isProductInStock ;
    private int productQuantityInStock ;
    private boolean isTopSelling;
    private List<RatingResponseDto> productRatings;
    private List<SizeDto> productSizes;
    private List<ImageDto> productImages;



}
