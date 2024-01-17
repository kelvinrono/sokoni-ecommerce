package com.project.sokoni.controllers;

import com.project.sokoni.DTOs.CategoryDto;
import com.project.sokoni.services.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
