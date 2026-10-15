package com.ecomm.product.request;

import lombok.Data;

@Data
public class updateBrandRequest {
	private String name;
    private String about;
    private String logoUrl;
    private String logoPublicId;
    private String status;

}
