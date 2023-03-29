package com.myshopexample.model.dto.customer;

import com.myshopexample.model.customer.Role;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Data
public class CustomerInputDto {
    private Long id;
    private String name;
    private String password;
    private String phone;
    public CustomerInputDto(){}

    public CustomerInputDto(String name, String password, String phone) {
        this.name = name;
        this.password = password;
        this.phone = phone;
    }
}
