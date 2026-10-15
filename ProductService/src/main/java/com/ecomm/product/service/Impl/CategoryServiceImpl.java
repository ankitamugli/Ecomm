package com.ecomm.product.service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecomm.product.dto.CategoryDto;
import com.ecomm.product.entity.Category;
import com.ecomm.product.repository.CategoryRepository;
import com.ecomm.product.request.addCategoryRequest;
import com.ecomm.product.request.updateCategoryRequest;
import com.ecomm.product.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto addCategory(addCategoryRequest request) {

        Category category = new Category();

        category.setCategoryName(request.getCategoryName());
        category.setAbout(request.getAbout());
        category.setParent(request.getParent());
        category.setImageUrl(request.getImageUrl());
        category.setImagePublicId(request.getImagePublicId());
        category.setStatus("ACTIVE");
        category.setCreatedAt(LocalDateTime.now());

        Category saved = categoryRepository.save(category);

        return mapToDto(saved);
    }

    @Override
    public CategoryDto updateCategory(
            Integer categoryId,
            updateCategoryRequest request) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        category.setCategoryName(request.getCategoryName());
        category.setAbout(request.getAbout());
        category.setParent(request.getParent());
        category.setImageUrl(request.getImageUrl());
        category.setImagePublicId(request.getImagePublicId());
        category.setStatus(request.getStatus());
        category.setUpdatedAt(LocalDateTime.now());

        Category updated = categoryRepository.save(category);

        return mapToDto(updated);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        return mapToDto(category);
    }

    @Override
    public void deleteByCategoryId(Integer categoryId) {

        if (!categoryRepository.existsById(categoryId)) {
            throw new RuntimeException("Category not found");
        }

        categoryRepository.deleteById(categoryId);
    }

    @Override
    public List<CategoryDto> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    private CategoryDto mapToDto(Category category) {

        return new CategoryDto(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getAbout(),
                category.getParent(),
                category.getImageUrl(),
                category.getImagePublicId(),
                category.getStatus(),
                category.getCreatedAt(),
                category.getUpdatedAt()
        );
    }
}