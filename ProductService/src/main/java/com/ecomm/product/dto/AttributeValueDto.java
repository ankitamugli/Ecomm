package com.ecomm.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttributeValueDto {

    private Integer attributeValueId;

    private String value;

    private Integer attributeId;
}