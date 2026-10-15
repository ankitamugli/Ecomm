package com.ecomm.product.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class updateProductImage {
	
	private String imageUrl;
	
	private String publicImageId;
	
    private Integer displayOrder;

}
