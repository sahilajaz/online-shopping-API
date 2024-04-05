package com.onlineshopping.productservice.repository;

import com.onlineshopping.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}

