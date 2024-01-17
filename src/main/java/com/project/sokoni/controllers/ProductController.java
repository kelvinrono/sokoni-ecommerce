package com.project.sokoni.controllers;

import com.project.sokoni.DTOs.ProductDto;
import com.project.sokoni.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
