package com.project.sokoni.DTOs;

import com.project.sokoni.models.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private String quantity;

    private List<Long> category;
}
