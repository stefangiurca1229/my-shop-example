package com.myshopexample.service.security;

import com.myshopexample.model.customer.Customer;
import com.myshopexample.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class PrincipalProvider {
    @Autowired
    private CustomerRepository customerRepository;
    public Customer getPrincipalProvider(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return customerRepository.findByName(username);
    }
}