package com.ecomm.product.request;

import lombok.Data;

@Data
public class addProductRequest {
	
	    private String productName;
	    
	    private String description;
	    
	    private Integer brandId;
	    
	    private Integer categoryId;
	

}
