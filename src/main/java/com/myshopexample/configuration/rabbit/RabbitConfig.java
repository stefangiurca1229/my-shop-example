package com.myshopexample.configuration.rabbit;

import com.myshopexample.service.order.ResolveOrder;
import com.myshopexample.service.order.SendOrderToQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue orderQueue(){
        return new Queue("orders");
    }
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("exchange.orders");
    }
    @Bean
    public Binding oredersQueueBind(DirectExchange directExchange, Queue orderQueue){
        return BindingBuilder.bind(orderQueue).to(directExchange).with("order");
    }
    @Bean
    public ResolveOrder reciveOrder(){
        return new ResolveOrder();
    }
    @Bean
    public SendOrderToQueue sendOrderToQueue(){
        return new SendOrderToQueue();
    }
}
