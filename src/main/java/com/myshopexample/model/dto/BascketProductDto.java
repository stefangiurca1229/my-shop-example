package com.myshopexample.model.dto;

import lombok.Data;

@Data
public class BascketProductDto {
    private int quantity;

    private ProductDto productDto;
}
