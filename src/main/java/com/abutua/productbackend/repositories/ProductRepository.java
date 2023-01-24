package com.abutua.productbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.abutua.productbackend.models.Product;

public interface ProductRepository extends JpaRepository <Product,Integer>{
}

