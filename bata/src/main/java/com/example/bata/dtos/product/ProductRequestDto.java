package com.example.bata.dtos.product;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequestDto {

    private Long productId;
    private String productName;
    private String productDescription;
    private double productPrice;
    private boolean isProductInStock = true;
    private int productQuantityInStock = 1;
    private boolean isTopSelling;
    private List<RatingDto> productRatings;
    private Long productCategoryId;
    private List<SizeDto> productSizes;


}
