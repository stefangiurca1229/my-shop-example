package com.myshopexample.security.service;

import com.myshopexample.model.dto.customer.CustomerDto;
import com.myshopexample.model.dto.customer.CustomerInputDto;
import com.myshopexample.model.mapper.CustomerMapper;
import com.myshopexample.security.model.MyCustomerPrincipal;
import com.myshopexample.model.customer.Customer;
import com.myshopexample.service.CustomerService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);
    @Autowired
    private CustomerService customerService;

    @Override
    public UserDetails loadUserByUsername(String customerName) throws UsernameNotFoundException {
        Customer customer = customerService.findByName(customerName);
        if(customer == null){
            throw new UsernameNotFoundException("customer not found!");
        }
        return new MyCustomerPrincipal(customer);
    }
}