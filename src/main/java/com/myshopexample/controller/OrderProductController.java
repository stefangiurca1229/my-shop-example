package com.myshopexample.controller;

import com.myshopexample.model.dto.OrderProductDto;
import com.myshopexample.responses.GenericResponse;
import com.myshopexample.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@RestController
public class OrderProductController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/get-orderProducts")
    public GenericResponse<Set<OrderProductDto>> getOrderProducts(
            @RequestParam Long orderId
    ){
        return orderService.getOrderProductsFromOrder(orderId);
    }
}
