package com.example.bata.dtos.product;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImageDto {
    private long productImageId;
    private String productImageType;
    private String productImageName;
    private String productImageUrl;
}
