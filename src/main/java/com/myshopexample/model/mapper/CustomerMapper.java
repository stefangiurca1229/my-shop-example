package com.myshopexample.model.mapper;

import com.myshopexample.model.customer.Customer;
import com.myshopexample.model.dto.customer.CustomerDto;
import com.myshopexample.model.dto.customer.CustomerInputDto;
import com.myshopexample.model.dto.customer.CustomerOutputDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    public CustomerOutputDto CustomerToCustomerOutputDto(Customer customer);
    public CustomerInputDto CustomerToCustomerInputDto(Customer customer);
    public CustomerDto CustomerToCustomerDto(Customer customer);
    public Customer CustomerDtoToCustomer(CustomerInputDto customerInputDto);
}
