package com.murilo.auth.dtos.product;

import com.murilo.auth.entities.Product;

public record GetProductDTO(Long id, String name, Integer price) {
    public GetProductDTO(Product product){
        this(product.getId(), product.getName(), product.getPrice());

    }

}
