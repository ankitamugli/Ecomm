package com.ecomm.product.service;

import java.util.List;

import com.ecomm.product.dto.CategoryDto;
import com.ecomm.product.request.addCategoryRequest;
import com.ecomm.product.request.updateCategoryRequest;

public interface CategoryService {

    CategoryDto addCategory(addCategoryRequest request);

    CategoryDto updateCategory(
            Integer categoryId,
            updateCategoryRequest request);

    CategoryDto getCategory(Integer categoryId);

    void deleteByCategoryId(Integer categoryId);

    List<CategoryDto> getAllCategories();
}