package com.murilo.auth.controllers;

import com.murilo.auth.entities.Product;
import com.murilo.auth.dtos.product.ProductLista;
import com.murilo.auth.dtos.product.ProductRequestDTO;
import com.murilo.auth.dtos.product.ProductResponseDTO;
import com.murilo.auth.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController()
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<ProductResponseDTO> postProduct(@RequestBody @Valid ProductRequestDTO data, UriComponentsBuilder uriBuilder) {
        var product = new Product(data);

        this.repository.save(product);
        var uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProductResponseDTO(product));
    }

    @GetMapping
    public ResponseEntity<List<ProductLista>> getAllProducts() {
        var productList = this.repository.findAll().stream().map(ProductLista::new).toList();

        return ResponseEntity.ok(productList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> detalhar(@PathVariable Long id) {
        var product = repository.getReferenceById(id);

        return ResponseEntity.ok(new ProductResponseDTO(product));
    }
}