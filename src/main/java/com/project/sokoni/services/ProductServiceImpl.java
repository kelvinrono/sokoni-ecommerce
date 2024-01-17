package com.project.sokoni.services;

import com.project.sokoni.DTOs.CategoryDto;
import com.project.sokoni.DTOs.ProductDto;
import com.project.sokoni.models.Category;
import com.project.sokoni.models.Product;
import com.project.sokoni.repositories.CategoryRepository;
import com.project.sokoni.repositories.ProductRepository;
import com.smattme.requestvalidator.RequestValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public HashMap addProduct(ProductDto productDto) {

        HashMap<String, Object> response = new HashMap<>();
        List<Category> categories  = new ArrayList<>();

        try {

            Product product = new Product();
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setQuantity(productDto.getQuantity());
            product.setDescription(productDto.getDescription());

            List<Long> categoryIds = productDto.getCategory();

            for(Long id: categoryIds){
                Category category = categoryRepository.findById(id).orElseThrow(() -> new IllegalStateException("Category with that id does not exist"));
                categories.add(category);
            }

            productRepository.save(product);

            log.info("Product saved successfully:::");

            response.put("message", "product saved successfully");
            response.put("status", true);
        }
        catch (Exception e){
            log.error("Something went wrong---->"+e.getMessage());
            e.printStackTrace();
            response.put("message", "Something went wrong");
            response.put("status","false");
        }
        return response;
    }

    @Override
    public HashMap validateProduct(ProductDto params) {

        HashMap<String, Object> response = new HashMap<>();

        HashMap<String, String> rules = new HashMap<>();

        try {
            rules.put("name", "required");
            rules.put("description", "required");
            rules.put("price", "required");
            rules.put("quantity", "required");
            rules.put("category", "required");

            List<String> errors = RequestValidator.validate(params, rules);
            if (!errors.isEmpty()) {
                response.put("message", "All fields are required");
                response.put("errors", errors);
                response.put("status", false);
                log.info("Some fields are missing for adding category---->"+errors);
                return response;
            } else {
                log.info("Parameters validated");
                return addProduct(params);
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
            response.put("message", "Oops! An error occurred!");
            response.put("status", false);

            return response;
        }
    }
}
