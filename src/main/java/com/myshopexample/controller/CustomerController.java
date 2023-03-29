package com.myshopexample.controller;

import com.myshopexample.model.customer.Role;
import com.myshopexample.model.dto.customer.CustomerInputDto;
import com.myshopexample.model.dto.customer.CustomerOutputDto;
import com.myshopexample.responses.GenericResponse;
import com.myshopexample.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/save-new-customer")
    public GenericResponse<CustomerOutputDto> saveNewCustomer(@RequestBody CustomerInputDto customer){
        return customerService.saveNewCustomer(customer, Role.ROLE_USER);
    }
}
