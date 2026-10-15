package com.ecomm.product.service;

import java.util.List;

import com.ecomm.product.dto.AttributeValueDto;
import com.ecomm.product.request.addAttributeValueRequest;
import com.ecomm.product.request.updateProductRequest;
public interface AttributeValueService {
	AttributeValueDto addValue(addAttributeValueRequest request);

    AttributeValueDto updateValue(Integer valueId,updateProductRequest request);

    AttributeValueDto getValue(Integer valueId);

    void deleteValue(Integer valueId);

    List<AttributeValueDto> getAllValues();

}
