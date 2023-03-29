package com.myshopexample.repositories.order;

import com.myshopexample.model.order.OrderProduct;
import com.myshopexample.model.order.OrderProductPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
