package com.myshopexample.controller;

import com.myshopexample.model.dto.OrderDto;
import com.myshopexample.responses.GenericResponse;
import com.myshopexample.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/customer-get-orders")
    public GenericResponse<List<OrderDto>> getCustomerOrders(){
        return orderService.getOrders();
    }
    @PostMapping("/send-order-with-querys")
    public GenericResponse<OrderDto> sendOrder(){
        return orderService.prepareOrder();
    }

    @PostMapping("/send-order-pure-jpa")
    public GenericResponse<OrderDto> sendOrderWithPureJpa(){
        return orderService.prepareOrderWithPureJpa();
    }
}
