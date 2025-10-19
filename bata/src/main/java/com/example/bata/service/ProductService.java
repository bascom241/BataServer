package com.example.bata.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.bata.dtos.product.CategoryDto;
import com.example.bata.dtos.product.ImageDto;
import com.example.bata.dtos.product.ProductRequestDto;
import com.example.bata.dtos.product.ProductResponseDto;
import com.example.bata.mapper.ProductMapper;
import com.example.bata.model.Product;
import com.example.bata.model.ProductCategory;
import com.example.bata.model.ProductImages;
import com.example.bata.repository.CategoryRepository;
import com.example.bata.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private CategoryRepository categoryRepository;



    public void addProduct(ProductRequestDto productRequestDto , List<MultipartFile> imageFiles) throws IOException {

        Product newProduct = productMapper.toEntity(productRequestDto);

        ProductCategory productCategory = categoryRepository.findById(productRequestDto.getProductCategoryId())
                .orElseThrow(()-> new RuntimeException("Category not found"));

        newProduct.setProductCategory(productCategory);

        if(imageFiles != null && !imageFiles.isEmpty()){
            List<ProductImages> productImages = new ArrayList<>();
            for (MultipartFile imageFile : imageFiles){
                Map<?,?> result = cloudinary.uploader().upload(imageFile.getBytes(), ObjectUtils.emptyMap());

                ProductImages productImage = new ProductImages();
                productImage.setProductImageName(imageFile.getOriginalFilename());
                productImage.setProductImageType(imageFile.getContentType());
                productImage.setProductImageUrl(result.get("secure_url").toString());
                productImages.add(productImage);
            }
           newProduct.setProductImages(productImages);
        }
        productRepository.save(newProduct);
    }


  public List<ProductResponseDto> getTopSellingProducts() {
        List<Product> products = productRepository.findAll();

        List<Product> topSellingProducts = products.stream().filter(Product::isTopSelling).toList();

        return topSellingProducts.stream().map(productMapper::productResponseDto).toList();
  }

}
