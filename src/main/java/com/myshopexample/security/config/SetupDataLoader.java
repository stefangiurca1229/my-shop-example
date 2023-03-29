package com.myshopexample.security.config;

import com.myshopexample.model.customer.Customer;
import com.myshopexample.model.customer.Role;
import com.myshopexample.model.dto.customer.CustomerInputDto;
import com.myshopexample.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = false;
    @Autowired
    private CustomerService customerService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(!alreadySetup){
            CustomerInputDto customerInputDtoUser = new CustomerInputDto("user","user","03294894");
            customerService.saveNewCustomer(customerInputDtoUser,Role.ROLE_USER);
        }
        alreadySetup = true;
    }
}
