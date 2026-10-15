package com.ecomm.product.service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecomm.product.dto.ProductDto;
import com.ecomm.product.entity.Brand;
import com.ecomm.product.entity.Category;
import com.ecomm.product.entity.Product;
import com.ecomm.product.repository.BrandRepository;
import com.ecomm.product.repository.CategoryRepository;
import com.ecomm.product.repository.ProductRepository;
import com.ecomm.product.request.addProductRequest;
import com.ecomm.product.request.updateProductRequest;
import com.ecomm.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final BrandRepository brandRepository;

    private final CategoryRepository categoryRepository;

    @Override
    public ProductDto addProduct(
            addProductRequest request) {

        Brand brand = brandRepository
                .findById(request.getBrandId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Brand not found"));

        Category category = categoryRepository
                .findById(request.getCategoryId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Category not found"));

        Product product = new Product();

        product.setProductName(
                request.getProductName());

        product.setDescription(
                request.getDescription());

        product.setStatus("ACTIVE");

        product.setBrand(brand);

        product.setCategory(category);

        product.setCreatedAt(
                LocalDateTime.now());

        Product savedProduct =
                productRepository.save(product);

        return mapToDto(savedProduct);
    }

    @Override
    public ProductDto updateProduct(
            Integer productId,
            updateProductRequest request) {

        Product product =
                productRepository.findById(productId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Product not found"));

        product.setProductName(
                request.getProductName());

        product.setDescription(
                request.getDescription());

        product.setStatus(
                request.getStatus());

        if (request.getBrandId() != null) {

            Brand brand =
                    brandRepository
                    .findById(request.getBrandId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Brand not found"));

            product.setBrand(brand);
        }

        if (request.getCategoryId() != null) {

            Category category =
                    categoryRepository
                    .findById(request.getCategoryId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Category not found"));

            product.setCategory(category);
        }

        product.setUpdatedAt(
                LocalDateTime.now());

        Product updatedProduct =
                productRepository.save(product);

        return mapToDto(updatedProduct);
    }

    @Override
    public ProductDto getProduct(
            Integer productId) {

        Product product =
                productRepository.findById(productId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Product not found"));

        return mapToDto(product);
    }

    @Override
    public void deleteProduct(
            Integer productId) {

        if (!productRepository.existsById(productId)) {

            throw new RuntimeException(
                    "Product not found");
        }

        productRepository.deleteById(productId);
    }

    @Override
    public List<ProductDto> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    private ProductDto mapToDto(
            Product product) {

        Integer brandId = null;
        Integer categoryId = null;

        if (product.getBrand() != null) {
            brandId = product.getBrand().getBrandId();
        }

        if (product.getCategory() != null) {
            categoryId =
                    product.getCategory().getCategoryId();
        }

        return new ProductDto(
                product.getProductId(),
                product.getProductName(),
                product.getDescription(),
                product.getStatus(),
                brandId,
                categoryId,
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}