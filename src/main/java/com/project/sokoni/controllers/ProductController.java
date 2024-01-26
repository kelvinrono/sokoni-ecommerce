package com.project.sokoni.controllers;

import com.project.sokoni.DTOs.PageObject;
import com.project.sokoni.DTOs.ProductDto;
import com.project.sokoni.models.Product;
import com.project.sokoni.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/v1/sokoni/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add-product")
    public HashMap addProduct(@RequestBody ProductDto productDto){
        log.info("Incoming request for adding products---->"+ productDto);
        return productService.validateProduct(productDto);
    }

    @GetMapping("/get-all-products")
    public HashMap getAllProducts(@RequestBody PageObject pageObject){
        log.info("----------Incoming request for getting all the products--------------"+pageObject);
        return productService.getAllProducts(pageObject);
    }

    @GetMapping("/get-product/{id}")
    public HashMap getProduct(@PathVariable Long id){

        log.info("Incoming request for getting a single product------->"+id);
        return productService.getProduct(id);
    }

    @DeleteMapping("/delete-product/{id}")
    public HashMap deleteProduct(@PathVariable Long id){
        log.info("Incoming request for getting a single product------->"+id);
        return productService.deleteProduct(id);
    }

    @PostMapping("/add-to-cart")
    public HashMap addToCart(@RequestParam("productId") Long productId, @RequestParam("userId") Long userId){
        log.info("Incoming request for adding to cart----> id: "+productId);
        return productService.addToCart(productId, userId);
    }


}
