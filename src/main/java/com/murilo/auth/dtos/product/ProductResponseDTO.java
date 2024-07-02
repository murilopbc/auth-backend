package com.murilo.auth.dtos.product;

import com.murilo.auth.entities.Product;

public record ProductResponseDTO(Long id, String name, Integer price) {
    public ProductResponseDTO(Product product){
        this(product.getId(), product.getName(), product.getPrice());
    }
}