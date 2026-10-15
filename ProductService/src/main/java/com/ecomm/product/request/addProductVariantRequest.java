package com.ecomm.product.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class addProductVariantRequest {

    private String sku;
    
    private Double price;
    
    private Integer productId;
    
    private List<Integer> attributeValueIds;
}
