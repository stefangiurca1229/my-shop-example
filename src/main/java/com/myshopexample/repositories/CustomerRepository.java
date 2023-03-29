package com.myshopexample.repositories;

import com.myshopexample.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByName(String name);

    Customer findByNameAndPassword(String customerName, String customerPassword);
}
