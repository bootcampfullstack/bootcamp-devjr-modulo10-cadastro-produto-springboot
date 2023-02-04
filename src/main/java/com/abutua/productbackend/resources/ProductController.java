package com.abutua.productbackend.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

 import com.abutua.productbackend.models.Product;
import com.abutua.productbackend.services.ProductService;

@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService; 

    @PostMapping("products")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        product = productService.save(product);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();
 
        return ResponseEntity.created(location).body(product);
    }

    @GetMapping("products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        Product product = productService.getById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("products")  
    public List<Product> getProducts() { 
        return productService.getAll();
    }

    @DeleteMapping("products/{id}")    
    public ResponseEntity<Void> removeProduct(@PathVariable int id) {
        productService.deleteById(id);                
        return ResponseEntity.noContent().build(); 
    }
   
    @PutMapping("products/{id}")    
    public ResponseEntity<Void> updateProduct(@PathVariable int id, @RequestBody Product productUpdate) {
        productService.update(id, productUpdate);
        return ResponseEntity.ok().build(); 
    }
    

}
