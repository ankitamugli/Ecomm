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

import com.ecomm.product.dto.ProductAttributeDto;
import com.ecomm.product.request.addProductAttributeRequest;
import com.ecomm.product.request.updateProductAttributeRequest;
import com.ecomm.product.service.ProductAttributeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/attributes")
@RequiredArgsConstructor
public class ProductAttributeController {

    private final ProductAttributeService attributeService;

    @PostMapping
    public ResponseEntity<ProductAttributeDto>
    addAttribute(
            @RequestBody addProductAttributeRequest request) {

        return ResponseEntity.ok(
                attributeService.addAttribute(request));
    }

    @PutMapping("/{attributeId}")
    public ResponseEntity<ProductAttributeDto>
    updateAttribute(
            @PathVariable Integer attributeId,
            @RequestBody updateProductAttributeRequest request) {

        return ResponseEntity.ok(
                attributeService.updateAttribute(
                        attributeId,
                        request));
    }

    @GetMapping("/{attributeId}")
    public ResponseEntity<ProductAttributeDto>
    getAttribute(
            @PathVariable Integer attributeId) {

        return ResponseEntity.ok(
                attributeService.getAttribute(attributeId));
    }

    @GetMapping
    public ResponseEntity<List<ProductAttributeDto>>
    getAllAttributes() {

        return ResponseEntity.ok(
                attributeService.getAllAttributes());
    }

    @DeleteMapping("/{attributeId}")
    public ResponseEntity<Void> deleteAttribute(
            @PathVariable Integer attributeId) {

        attributeService.deleteAttribute(attributeId);

        return ResponseEntity.noContent().build();
    }
}