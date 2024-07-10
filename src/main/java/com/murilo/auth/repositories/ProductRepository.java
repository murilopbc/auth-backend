package com.murilo.auth.repositories;

import com.murilo.auth.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByActiveTrue();
    Optional<Product> findByName(String name);
}
