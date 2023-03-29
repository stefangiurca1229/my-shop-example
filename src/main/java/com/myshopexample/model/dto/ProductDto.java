package com.myshopexample.model.dto;

import com.myshopexample.model.product.Category;
import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private String thumbnail;
    private Category category;
}
