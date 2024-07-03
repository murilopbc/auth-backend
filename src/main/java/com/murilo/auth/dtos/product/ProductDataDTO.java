package com.murilo.auth.dtos.product;

import com.murilo.auth.entities.Product;

public record ProductDataDTO(Long id, String name, Integer price, Boolean active) {
    public ProductDataDTO(Product product){
        this(product.getId(), product.getName(), product.getPrice(), product.getActive());
    }
}