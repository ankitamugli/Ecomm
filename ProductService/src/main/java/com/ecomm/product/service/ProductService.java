package com.ecomm.product.service;

import java.util.List;

import com.ecomm.product.dto.ProductDto;
import com.ecomm.product.request.addProductRequest;
import com.ecomm.product.request.updateProductRequest;

public interface ProductService {
	ProductDto addProduct(addProductRequest request);

    ProductDto updateProduct(Integer productId,updateProductRequest request);

    ProductDto getProduct(Integer productId);

    void deleteProduct(Integer productId);

    List<ProductDto> getAllProducts();
	
	

}
