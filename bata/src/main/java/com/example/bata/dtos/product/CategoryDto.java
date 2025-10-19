package com.example.bata.dtos.product;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Long productCategoryId;
    private String productCategoryName;
    private String productCategoryImageName;
    private String productCategoryImageType;
    private String productCategoryImageUrl;
}
