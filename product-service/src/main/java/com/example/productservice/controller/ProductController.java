package com.example.productservice.controller;

import com.example.productservice.entity.Product;
import com.example.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
//ProductController handles all HTTP requests related to Product operations

@RestController
@RequestMapping("/products")// Base URL for all product-related endpoints
@CrossOrigin("*")// Allows frontend apps from any origin to access this API


public class ProductController {
//Service layer object used to perform business logic
    private final ProductService productService;
    //Constructor-based dependency injection

    public ProductController(ProductService productService) {
        this.productService = productService;
        //Fetch all products from the database
    }
//database crud operations for products 
    @GetMapping
    //Fetch a single product by its ID
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "Product deleted successfully";
    }
}