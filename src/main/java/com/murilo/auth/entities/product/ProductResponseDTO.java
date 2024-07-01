package com.murilo.auth.entities.product;

public record ProductResponseDTO(Long id, String name, Integer price) {
    public ProductResponseDTO(Product product){
        this(product.getId(), product.getName(), product.getPrice());
    }
}