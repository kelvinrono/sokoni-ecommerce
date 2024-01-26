package com.project.sokoni.services;

import com.project.sokoni.DTOs.CategoryDto;
import com.project.sokoni.DTOs.PageObject;
import com.project.sokoni.models.Category;
import com.project.sokoni.models.Product;
import com.project.sokoni.repositories.CategoryRepository;
import com.smattme.requestvalidator.RequestValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public HashMap addCategory(CategoryDto categoryDto) {
        Category category = new Category();

        HashMap<String, Object> response = new HashMap<>();
        try {

            category.setName(categoryDto.getName());
            category.setDescription(categoryDto.getDescription());

            log.info(category.toString());
            categoryRepository.save(category);
            response.put("message", "Category has been saved sucessfully");
            response.put("status", true);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Something went wrong---->" + e.getMessage());
            response.put("message", "Something went wrong");
            response.put("status", false);
            return response;
        }

    }

    //Validate Category request
    @Override
    public HashMap validateCategory(CategoryDto params) {

        HashMap<String, Object> response = new HashMap<>();

        HashMap<String, String> rules = new HashMap<>();

        try {
            rules.put("name", "required");
            rules.put("description", "required");

            List<String> errors = RequestValidator.validate(params, rules);
            if (!errors.isEmpty()) {
                response.put("message", "All fields are required");
                response.put("errors", errors);
                response.put("status", false);
                log.info("Some fields are missing for adding category---->"+errors);
                return response;
            } else {
                log.info("Parameters validated");
                return addCategory(params);
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
            response.put("message", "Oops! An error occurred!");
            response.put("status", false);

            return response;
        }
    }

    @Override
    public HashMap getAllCategories(int pageNumber, int pageSize) {

        HashMap<String, Object> response = new HashMap<>();
        try {
            PageRequest page = PageRequest.of(pageNumber, pageSize, Sort.by("createdAt").descending());
            Page<Category> categories = categoryRepository.findAll(page);

            response.put("message", "Categories retrived successfully");
            response.put("status", "true");
            response.put("data", response);
        }
        catch (Exception e){
            e.printStackTrace();
            response.put("message", "Oops! Something went went wrong!");
            response.put("status", true);
            log.error("Something went wrong while fetching categories------------------>"+ e.getMessage());
        }
        return response;
    }

    @Override
    public HashMap getCategory(Long id) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            Category category = categoryRepository.findById(id).orElseThrow(()-> new IllegalStateException("Category with the given id does not exist"));
            response.put("message", "Category retrieved successfully");
            response.put("status", true);
            response.put("data", category);

        }catch (Exception e){
            e.printStackTrace();
            log.error("An error occurred while getting single product---->"+e.getMessage());
            response.put("message", "Something went wrong");
            response.put("status", false);
        }
        return response;
    }

    @Override
    public HashMap deleteCategory(Long id) {
        HashMap<String, Object> response = new HashMap<>();
        try {

            Category product = categoryRepository.findById(id).orElseThrow(()-> new IllegalStateException("No Category with the given id given to be deleted-"));
            categoryRepository.delete(product);
            response.put("message", "category deleted successfully");
            response.put("status", true);

        }
        catch (Exception e){
            e.printStackTrace();
            log.error("An error occurred while deleting a category"+e.getMessage());
            response.put("message", "Oops! Something went wrong");
            response.put("status", false);
        }
        return response;
    }
}
