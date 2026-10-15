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
public class BrandDto {

    private Integer brandId;

    private String name;

    private String about;

    private String logoUrl;

    private String logoPublicId;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}