package com.abutua.productbackend.resources;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.abutua.productbackend.models.Category;
import com.abutua.productbackend.repositories.CategoryRepository;

@RestController
@CrossOrigin
public class CategoryController {
 
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("categories/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable int id) {
        Category category = categoryRepository.findById(id)
                                         .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        
        
        return ResponseEntity.ok(category);
    }

    @GetMapping("categories")
    public List<Category> getCategories() { 
        return categoryRepository.findAll();  
    }
    
}
