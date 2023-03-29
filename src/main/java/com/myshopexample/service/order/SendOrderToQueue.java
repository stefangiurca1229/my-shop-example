package com.myshopexample.service.order;

import com.myshopexample.model.customer.Customer;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class SendOrderToQueue {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private DirectExchange directExchange;
    public void sendOrder(Customer customer){
        rabbitTemplate.convertAndSend(directExchange.getName(),"order",customer);
    }
}
