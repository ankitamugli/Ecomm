package com.ecomm.product.service;

import java.util.List;

import com.ecomm.product.dto.ProductAttributeDto;
import com.ecomm.product.request.addProductAttributeRequest;
import com.ecomm.product.request.updateProductAttributeRequest;

public interface ProductAttributeService {

    ProductAttributeDto addAttribute(
            addProductAttributeRequest request);

    ProductAttributeDto updateAttribute(
            Integer attributeId,
            updateProductAttributeRequest request);

    ProductAttributeDto getAttribute(Integer attributeId);

    void deleteAttribute(Integer attributeId);

    List<ProductAttributeDto> getAllAttributes();
}