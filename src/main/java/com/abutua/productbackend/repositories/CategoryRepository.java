package com.abutua.productbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.abutua.productbackend.models.Category;

public interface CategoryRepository extends JpaRepository <Category,Integer>{
}



