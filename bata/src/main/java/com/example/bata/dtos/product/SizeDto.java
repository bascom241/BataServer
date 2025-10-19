package com.example.bata.dtos.product;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SizeDto {
    private long productSizeId;
    private String sizeLabel; // e.g. "Small", "Medium", "Large"
    private int quantityAvailable;
}
