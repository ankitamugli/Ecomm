package com.ecomm.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomm.product.dto.ProductDto;
import com.ecomm.product.request.addProductRequest;
import com.ecomm.product.request.updateProductRequest;
import com.ecomm.product.response.ApiResponse;
import com.ecomm.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(
            @RequestBody addProductRequest request) {

        ProductDto dto =
                productService.addProduct(request);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "data added successfully",
                        dto,
                        HttpStatus.OK));
    }

    @PutMapping("/{pid}")
    public ResponseEntity<?> updateProduct(
            @PathVariable Integer pid,
            @RequestBody updateProductRequest request) {

        ProductDto dto =
                productService.updateProduct(
                        pid,
                        request);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{pid}")
    public ResponseEntity<?> getById(
            @PathVariable Integer pid) {

        ProductDto dto =
                productService.getProduct(pid);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {

        return ResponseEntity.ok(
                productService.getAllProducts());
    }

    @DeleteMapping("/{pid}")
    public ResponseEntity<?> delete(
            @PathVariable Integer pid) {

        productService.deleteProduct(pid);

        return ResponseEntity.ok(
                "delete successfully");
    }
}