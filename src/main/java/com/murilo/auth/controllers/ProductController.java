package com.murilo.auth.controllers;

import com.murilo.auth.dtos.product.PutProductDTO;
import com.murilo.auth.entities.Product;
import com.murilo.auth.dtos.product.GetProductDTO;
import com.murilo.auth.dtos.product.PostProductDTO;
import com.murilo.auth.dtos.product.ProductDataDTO;
import com.murilo.auth.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<ProductDataDTO> postProduct(@RequestBody @Valid PostProductDTO data, UriComponentsBuilder uriBuilder) {

        Optional<Product> existingProduct = repository.findByName(data.name());

        if (existingProduct.isPresent()) {

            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        var product = new Product(data);
        repository.save(product);

        var uri = uriBuilder.path("/product/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProductDataDTO(product));
    }

    @GetMapping
    public ResponseEntity<List<GetProductDTO>> getAllProducts() {
        var product = this.repository.findAllByActiveTrue().stream().map(GetProductDTO::new).toList();

        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDataDTO> getProductById(@PathVariable Long id) {
        var product = repository.getReferenceById(id);

        return ResponseEntity.ok(new ProductDataDTO(product));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ProductDataDTO> putProduct(@RequestBody @Valid PutProductDTO dados){
        var product = repository.getReferenceById(dados.id());
        product.atualizarInformacoes(dados);

        return ResponseEntity.ok(new ProductDataDTO(product));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("inativar/{id}")
    @Transactional
    public ResponseEntity<Void> inactivateProduct(@PathVariable Long id){
        var product = repository.getReferenceById(id);
        product.inactivate();

        return ResponseEntity.noContent().build();
    }

    @PutMapping("ativar/{id}")
    @Transactional
    public ResponseEntity<Void> activateProduct(@PathVariable Long id){
        var product = repository.getReferenceById(id);
        product.activate();

        return ResponseEntity.noContent().build();
    }

}