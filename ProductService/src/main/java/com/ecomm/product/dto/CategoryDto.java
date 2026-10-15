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
public class CategoryDto {

    private Integer categoryId;

    private String categoryName;

    private String about;

    private String parent;

    private String imageUrl;

    private String imagePublicId;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}