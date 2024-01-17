package com.project.sokoni.services;

import com.project.sokoni.DTOs.ProductDto;

import java.util.HashMap;

public interface ProductService {
    HashMap addProduct(ProductDto productDto);
    HashMap validateProduct(ProductDto productDto);
}
