package com.project.sokoni.services;

import com.project.sokoni.DTOs.CategoryDto;
import com.project.sokoni.DTOs.PageObject;
import com.project.sokoni.models.Category;
import com.project.sokoni.responses.Message;

import java.util.HashMap;

public interface CategoryService {

    HashMap addCategory(CategoryDto categoryDto);

    HashMap validateCategory(CategoryDto categoryDto);


    HashMap getAllCategories(int pageNumber, int pageSize);

    HashMap getCategory(Long id);

    HashMap deleteCategory(Long id);
}
