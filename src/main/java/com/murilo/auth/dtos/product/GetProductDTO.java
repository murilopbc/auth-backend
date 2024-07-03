package com.murilo.auth.dtos.product;

import com.murilo.auth.entities.Product;

public record GetProductDTO(String name, Integer price) {
    public GetProductDTO(Product product){
        this(product.getName(), product.getPrice());

    }

}
