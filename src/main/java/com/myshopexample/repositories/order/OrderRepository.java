package com.myshopexample.repositories.order;

import com.myshopexample.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
//    List<Order> findOrdersByCustomer_Id(Long customerId);
//
//    Order findOrderByCustomer_IdAndId(Long cusomterId,Long orderId);

    List<Order> findOrdersByCustomerName(String customerName);

    Order findOrderByCustomerIdAndId(String name, Long orderId);

    List<Order> findOrdersByCustomerId(Long id);
}
