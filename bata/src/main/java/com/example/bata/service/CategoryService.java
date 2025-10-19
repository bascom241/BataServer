package com.example.bata.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.bata.configurations.CloudinaryConfig;
import com.example.bata.dtos.product.CategoryDto;
import com.example.bata.model.ProductCategory;
import com.example.bata.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private Cloudinary cloudinary;
    public void addCategory(CategoryDto categoryDto , MultipartFile categoryImage) throws IOException {


       if(categoryImage != null &&!categoryImage.isEmpty()){
           Map<?,?> result = cloudinary.uploader().upload(categoryImage.getBytes(), ObjectUtils.emptyMap());
           categoryDto.setProductCategoryImageName(categoryImage.getOriginalFilename());
           categoryDto.setProductCategoryImageType(categoryImage.getContentType());
           categoryDto.setProductCategoryImageUrl(result.get("secure_url").toString());

       }

       ProductCategory productCategory = new ProductCategory();

       productCategory.setProductCategoryName(categoryDto.getProductCategoryName());
       productCategory.setProductCategoryImageType(categoryDto.getProductCategoryImageType());
       productCategory.setProductCategoryImageName(categoryDto.getProductCategoryImageName());
       productCategory.setProductCategoryImageUrl(categoryDto.getProductCategoryImageUrl());


       categoryRepository.save(productCategory);

    }
}
