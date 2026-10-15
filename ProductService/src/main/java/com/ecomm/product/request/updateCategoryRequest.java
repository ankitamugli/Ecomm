package com.ecomm.product.request;

import lombok.Data;

@Data
public class updateCategoryRequest {
	
	private String categoryName;
    private String about;
    private String parent;
    private String imageUrl;
    private String imagePublicId;
    private String status;

}
