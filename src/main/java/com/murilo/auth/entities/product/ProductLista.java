package com.murilo.auth.entities.product;

public record ProductLista(String name, Integer price ) {
    public ProductLista(Product product){
        this(product.getName(), product.getPrice());

    }

}
