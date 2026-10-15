package com.ecomm.product.service;

import java.util.List;

import com.ecomm.product.dto.BrandDto;
import com.ecomm.product.request.addBrandRequest;
import com.ecomm.product.request.updateBrandRequest;

public interface BrandService {

    BrandDto addBrand(addBrandRequest request);

    BrandDto updateBrand(Integer brandId, updateBrandRequest request);

    BrandDto getBrand(Integer brandId);

    void deleteByBrandId(Integer brandId);

    List<BrandDto> getAllBrand();
}