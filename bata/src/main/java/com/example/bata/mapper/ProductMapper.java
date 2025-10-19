package com.example.bata.mapper;

import com.example.bata.dtos.product.*;
import com.example.bata.dtos.user.UserResponseDto;
import com.example.bata.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public Product toEntity (ProductRequestDto productRequestDto){
        Product product = new Product();
        product.setProductId(productRequestDto.getProductId());
        product.setProductName(productRequestDto.getProductName());
        product.setProductDescription(productRequestDto.getProductDescription());
        product.setProductPrice(productRequestDto.getProductPrice());
        product.setProductInStock(productRequestDto.isProductInStock());
        product.setProductQuantityInStock(productRequestDto.getProductQuantityInStock());
        product.setTopSelling(productRequestDto.isTopSelling());




        // sizes Looping
        List<ProductSizes> sizes = productRequestDto.getProductSizes().stream()
                .map(sizeDto -> {
                    ProductSizes productSizes = new ProductSizes();
                    productSizes.setProductSizeId(sizeDto.getProductSizeId());
                    productSizes.setSizeLabel(sizeDto.getSizeLabel());
                    productSizes.setQuantityAvailable(sizeDto.getQuantityAvailable());
                    productSizes.setProduct(product);
                    return productSizes;
                }).toList();
        product.setProductSizes(sizes);



        // rating Looping  To be implemented from the normal user
//        List<ProductRating> productRatings = productRequestDto.getProductRatings().stream()
//                .map(ratingDto -> {
//                    ProductRating productRating = new ProductRating();
//                    productRating.setProductRatingId(ratingDto.getProductRatingId());
//                    productRating.setProductRating(ratingDto.getProductRating());
//                    productRating.setProductComment(ratingDto.getProductComment());
//                    productRating.setProductRaterFirstName(ratingDto.getUser().getFirstName());
//                    productRating.setProduct(product);
//                    return productRating;
//                }).toList();
//        product.setProductRatings(productRatings);

        return product;
    }


    public ProductResponseDto productResponseDto(Product product){
        return new ProductResponseDto(
                product.getProductId(),
                product.getProductName(),
                product.getProductDescription(),
                product.getProductPrice(),
                product.isProductInStock(),
                product.getProductQuantityInStock(),
                product.isTopSelling(),
                product.getProductRatings().stream().map(rating -> new RatingResponseDto(
                        rating.getProductRating(),
                        rating.getProductComment(),
                        rating.getProductRaterFirstName()
                )).toList(),
                product.getProductSizes().stream().map(size -> new SizeDto(
                        size.getProductSizeId(),
                        size.getSizeLabel(),
                        size.getQuantityAvailable()
                )).toList(),

                product.getProductImages().stream().map(image -> new ImageDto(
                        image.getProductImageId(),
                        image.getProductImageType(),
                        image.getProductImageName(),
                        image.getProductImageUrl()
                )).toList()
        );


    }

}
