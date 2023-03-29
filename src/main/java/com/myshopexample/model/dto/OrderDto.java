package com.myshopexample.model.dto;

import com.myshopexample.model.order.StatusOrder;
import lombok.Data;

import java.time.LocalDate;
@Data
public class OrderDto {
    private Long id;
    private StatusOrder status;
    private LocalDate createDate;
}
