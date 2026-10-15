package com.ecomm.product.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductVarientDto {

    private Integer variantId;

    private String sku;

    private Double price;

    private String status;

    private Integer productId;

    private List<Integer> attributeValueIds;
}