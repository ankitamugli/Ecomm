package com.ecomm.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageDto {

    private Integer imageId;

    private String imageUrl;

    private String publicImageId;

    private Integer displayOrder;
}