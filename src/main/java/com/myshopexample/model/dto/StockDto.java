package com.myshopexample.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myshopexample.model.product.Product;
import lombok.Data;

import java.time.LocalDate;
@Data
public class StockDto {
    private Long id;
    private int quantity;
    private Double price;
    private Product product;
    private LocalDate createDate;
    private Boolean enable;
}
