package com.myshopexample.repositories.bascket;

import com.myshopexample.model.bascket.Bascket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BascketRepository extends JpaRepository<Bascket, Long> {
    public Bascket findBasketByCustomer_Id(Long id);

    Bascket findBasketByCustomerName(String customerName);
}
