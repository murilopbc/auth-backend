package com.murilo.auth.dtos.product;

import com.murilo.auth.entities.Product;

public record ProductLista(String name, Integer price ) {
    public ProductLista(Product product){
        this(product.getName(), product.getPrice());

    }

}
