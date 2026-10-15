package com.ecomm.product.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomm.product.dto.CategoryDto;
import com.ecomm.product.request.addCategoryRequest;
import com.ecomm.product.request.updateCategoryRequest;
import com.ecomm.product.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(
            @RequestBody addCategoryRequest request) {

        return ResponseEntity.ok(
                categoryService.addCategory(request));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable Integer categoryId,
            @RequestBody updateCategoryRequest request) {

        return ResponseEntity.ok(
                categoryService.updateCategory(
                        categoryId,
                        request));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(
            @PathVariable Integer categoryId) {

        return ResponseEntity.ok(
                categoryService.getCategory(categoryId));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {

        return ResponseEntity.ok(
                categoryService.getAllCategories());
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable Integer categoryId) {

        categoryService.deleteByCategoryId(categoryId);

        return ResponseEntity.noContent().build();
    }
}