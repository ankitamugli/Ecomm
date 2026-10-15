package com.ecomm.product.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomm.product.dto.BrandDto;
import com.ecomm.product.request.addBrandRequest;
import com.ecomm.product.request.updateBrandRequest;
import com.ecomm.product.service.BrandService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    public ResponseEntity<BrandDto> addBrand(
            @RequestBody addBrandRequest request) {

        return ResponseEntity.ok(
                brandService.addBrand(request));
    }

    @PutMapping("/{brandId}")
    public ResponseEntity<BrandDto> updateBrand(
            @PathVariable Integer brandId,
            @RequestBody updateBrandRequest request) {

        return ResponseEntity.ok(
                brandService.updateBrand(
                        brandId,
                        request));
    }

    @GetMapping("/{brandId}")
    public ResponseEntity<BrandDto> getBrand(
            @PathVariable Integer brandId) {

        return ResponseEntity.ok(
                brandService.getBrand(brandId));
    }

    @GetMapping
    public ResponseEntity<List<BrandDto>>
    getAllBrands() {

        return ResponseEntity.ok(brandService.getAllBrand());
    }

    @DeleteMapping("/{brandId}")
    public ResponseEntity<Void> deleteBrand(
            @PathVariable Integer brandId) {

        brandService.deleteByBrandId(brandId);

        return ResponseEntity.noContent().build();
    }
}