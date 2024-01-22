package com.project.sokoni.services;

import com.project.sokoni.DTOs.PageObject;
import com.project.sokoni.DTOs.ProductDto;
import com.project.sokoni.models.Category;
import com.project.sokoni.models.Product;
import com.project.sokoni.repositories.CategoryRepository;
import com.project.sokoni.repositories.ProductRepository;
import com.smattme.requestvalidator.RequestValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
            log.error("Something went wrong while saving a product---->"+e.getMessage());
            e.printStackTrace();
            response.put("message", "Something went wrong");
            response.put("status","false");
        }
        return response;
    }


    @Override
    public HashMap getAllProducts(PageObject pageObject) {

        HashMap<String, Object> response = new HashMap<>();
        try {
            PageRequest page = PageRequest.of(pageObject.getPageNumber(), pageObject.getPageSize(), Sort.by("createdAt").descending());
            Page<Product> products =  productRepository.findAll(page);
            response.put("message", "Data retrieved successully");
            response.put("data", products);
            response.put("status", true);

        }catch (Exception e){
            e.printStackTrace();
            response.put("message", "Something went wrong");
            response.put("status", false);
            log.error("Something went wrong while getting all products---->"+e.getMessage());
        }
        return  response;
    }

    @Override
    public HashMap getProduct(Long id) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            Product product = productRepository.findById(id).orElseThrow(()-> new IllegalStateException("Product with the given id does not exist"));
            response.put("message", "product retrieved successfully");
            response.put("status", true);
            response.put("data", product);

        }catch (Exception e){
            e.printStackTrace();
            log.error("An error occurred while getting single product---->"+e.getMessage());
            response.put("message", "Something went wrong");
            response.put("status", false);
        }
        return response;
    }

    @Override
    public HashMap deleteProduct(Long id) {
        HashMap<String, Object> response = new HashMap<>();
        try {

            Product product = productRepository.findById(id).orElseThrow(()-> new IllegalStateException("No product with the given id given to be deleted-"));
            response.put("message", "product deleted successfully");
            response.put("status", true);
            response.put("data", product);

        }
        catch (Exception e){
            e.printStackTrace();
            log.error("An error occurred while deleting a product"+e.getMessage());
            response.put("message", "Something went wrong while deleting a product");
            response.put("status", false);

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
