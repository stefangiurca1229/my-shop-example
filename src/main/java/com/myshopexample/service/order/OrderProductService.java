package com.myshopexample.service.order;

import com.myshopexample.model.order.OrderProduct;
import com.myshopexample.repositories.order.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OrderProductService {
    @Autowired
    private OrderProductRepository orderProductRepository;
    public OrderProduct save(OrderProduct orderProduct){
        return orderProductRepository.save(orderProduct);
    }

    public void saveAll(Set<OrderProduct> orderProducts) {
        orderProductRepository.saveAll(orderProducts);
    }
}
