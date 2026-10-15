package com.ecomm.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.product.entity.ProductImage;

@Repository
public interface ProductImageRepository  extends JpaRepository<ProductImage, Integer> {
}