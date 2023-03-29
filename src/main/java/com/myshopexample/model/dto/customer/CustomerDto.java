package com.myshopexample.model.dto.customer;

import com.myshopexample.model.customer.Role;
import lombok.Data;

import javax.persistence.*;

@Data
public class CustomerDto {
    private Long id;
    private String name;
    private String password;
    private String phone;
    private Role role;
}
