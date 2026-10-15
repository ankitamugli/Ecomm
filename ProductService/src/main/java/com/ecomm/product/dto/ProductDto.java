package com.ecomm.product.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Integer productId;

    private String productName;

    private String description;

    private String status;

    private Integer brandId;

    private Integer categoryId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}