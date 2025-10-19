package com.example.bata.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RatingResponseDto {
    private int productRating;
    private String productComment;
    private String firstName;
}
