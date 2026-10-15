package com.ecomm.product.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
public class updateAttributeValueRequest {

    private String value;
    
    private Integer attributeId;
}
