package com.ecomm.product.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class addProductImageRequest {
	
    private String imageUrl;
    
    private String publicImageId;
    
    private Integer displayOrder;
    
    private Integer productId;

}
