package com.abutua.productbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.abutua.productbackend.models.Category;
import com.abutua.productbackend.models.Product;
import com.abutua.productbackend.repositories.CategoryRepository;
import com.abutua.productbackend.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public Product getById(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        return product;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(int id) {
        Product product = getById(id);
        productRepository.delete(product);
    }

    public void update(int id, Product productUpdate) {
        Product product = getById(id);

        if (productUpdate.getCategory() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category can not be empty");
        }

        Category category = categoryService.getById(productUpdate.getCategory().getId());

        product.setDescription(productUpdate.getDescription());
        product.setName(productUpdate.getName());
        product.setPrice(productUpdate.getPrice());
        product.setNewProduct(productUpdate.isNewProduct());
        product.setPromotion(productUpdate.isPromotion());
        product.setCategory(category);

        productRepository.save(product);
    }

}
