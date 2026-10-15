package com.ecomm.product.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecomm.product.dto.ProductAttributeDto;
import com.ecomm.product.entity.ProductAttribute;
import com.ecomm.product.repository.ProductAttributeRepository;
import com.ecomm.product.request.addProductAttributeRequest;
import com.ecomm.product.request.updateProductAttributeRequest;
import com.ecomm.product.service.ProductAttributeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductAttributeServiceImpl
        implements ProductAttributeService {

    private final ProductAttributeRepository repository;

    @Override
    public ProductAttributeDto addAttribute(
            addProductAttributeRequest request) {

        ProductAttribute attribute =
                new ProductAttribute();

        attribute.setAttributeName(
                request.getAttributeName());

        return mapToDto(
                repository.save(attribute));
    }

    @Override
    public ProductAttributeDto updateAttribute(
            Integer attributeId,
            updateProductAttributeRequest request) {

        ProductAttribute attribute =
                repository.findById(attributeId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Attribute not found"));

        attribute.setAttributeName(request.getAttributeName());

        return mapToDto(repository.save(attribute));
    }

    @Override
    public ProductAttributeDto getAttribute(
            Integer attributeId) {

        ProductAttribute attribute =
                repository.findById(attributeId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Attribute not found"));

        return mapToDto(attribute);
    }

    @Override
    public void deleteAttribute(
            Integer attributeId) {

        if (!repository.existsById(attributeId)) {
            throw new RuntimeException(
                    "Attribute not found");
        }

        repository.deleteById(attributeId);
    }

    @Override
    public List<ProductAttributeDto>
    getAllAttributes() {

        return repository.findAll().stream().map(this::mapToDto).toList();
    }

    private ProductAttributeDto mapToDto(  ProductAttribute attribute) {

        return new ProductAttributeDto(attribute.getAttributeId(),attribute.getAttributeName());
    }
}