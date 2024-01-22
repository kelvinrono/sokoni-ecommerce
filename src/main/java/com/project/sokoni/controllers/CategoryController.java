package com.project.sokoni.controllers;

import com.project.sokoni.DTOs.CategoryDto;
import com.project.sokoni.DTOs.PageObject;
import com.project.sokoni.services.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("api/v1/sokoni/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add-category")
    HashMap addCategory(@RequestBody CategoryDto categoryDto){
        log.info("incoming request for adding category---->"+categoryDto);
        return categoryService.validateCategory(categoryDto);
    }


    @GetMapping("/get-all-categories")
    public HashMap getAllProducts(@RequestBody PageObject pageObject){
        log.info("----------Incoming request for getting all the categories--------------"+pageObject);
        return categoryService.getAllCategories(pageObject);
    }

    @GetMapping("/get-product/{id}")
    public HashMap getProduct(@PathVariable Long id){

        log.info("Incoming request for getting a single category------->"+id);
        return categoryService.getCategory(id);
    }

    @DeleteMapping("/delete-category/{id}")
    public HashMap deleteProduct(@PathVariable Long id){
        log.info("Incoming request for deleting a category------->"+id);
        return categoryService.deleteCategory(id);
    }
}
