package com.myshopexample.model.dto;

import com.myshopexample.model.product.Category;
import lombok.Data;

@Data
public class OrderProductDto {
    private Long id;
    private int quantity;
    private String title;
    private String description;
    private Double price;
    private Category category;
}
