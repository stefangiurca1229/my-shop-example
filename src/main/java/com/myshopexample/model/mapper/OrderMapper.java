package com.myshopexample.model.mapper;

import com.myshopexample.model.dto.OrderDto;
import com.myshopexample.model.order.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    public OrderDto OrderToOrderDto(Order order);
    public List<OrderDto> OrderToOrderDto(List<Order> order);
    public Order OrderDtoToOrder(OrderDto orderDto);
}
