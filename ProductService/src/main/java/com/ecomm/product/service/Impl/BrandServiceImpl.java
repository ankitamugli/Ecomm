package com.ecomm.product.service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecomm.product.dto.BrandDto;
import com.ecomm.product.entity.Brand;
import com.ecomm.product.repository.BrandRepository;
import com.ecomm.product.request.addBrandRequest;
import com.ecomm.product.request.updateBrandRequest;
import com.ecomm.product.service.BrandService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public BrandDto addBrand(addBrandRequest request) {

        Brand brand = new Brand();

        brand.setName(request.getName());
        brand.setAbout(request.getAbout());
        brand.setLogoUrl(request.getLogoUrl());
        brand.setLogoPublicId(request.getLogoPublicId());
        brand.setStatus("ACTIVE");
        brand.setCreatedAt(LocalDateTime.now());

        Brand saved = brandRepository.save(brand);

        return mapToDto(saved);
    }

    @Override
    public BrandDto updateBrand(
            Integer brandId,
            updateBrandRequest request) {

        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() ->
                        new RuntimeException("Brand not found"));

        brand.setName(request.getName());
        brand.setAbout(request.getAbout());
        brand.setLogoUrl(request.getLogoUrl());
        brand.setLogoPublicId(request.getLogoPublicId());
        brand.setStatus(request.getStatus());
        brand.setUpdatedAt(LocalDateTime.now());

        return mapToDto(
                brandRepository.save(brand));
    }

    @Override
    public BrandDto getBrand(Integer brandId) {

        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() ->
                        new RuntimeException("Brand not found"));

        return mapToDto(brand);
    }

    @Override
    public void deleteByBrandId(Integer brandId) {

        if (!brandRepository.existsById(brandId)) {
            throw new RuntimeException("Brand not found");
        }

        brandRepository.deleteById(brandId);
    }

    @Override
    public List<BrandDto> getAllBrand() {

        return brandRepository.findAll().stream().map(this::mapToDto) .toList();
    }

    private BrandDto mapToDto(Brand brand) {

        return new BrandDto(
                brand.getBrandId(),
                brand.getName(),
                brand.getAbout(),
                brand.getLogoUrl(),
                brand.getLogoPublicId(),
                brand.getStatus(),
                brand.getCreatedAt(),
                brand.getUpdatedAt()
        );
    }
}