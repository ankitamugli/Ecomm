package com.ecomm.product.request;

import lombok.Data;

@Data
public class updateProductRequest {

    private String productName;
    private String description;
    private Integer brandId;
    private Integer categoryId;
    private String status;

}
