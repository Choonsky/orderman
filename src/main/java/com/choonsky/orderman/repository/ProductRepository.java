package com.choonsky.orderman.repository;

import com.choonsky.orderman.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer>
{
    Optional<Product> findByProductName(String name);
}