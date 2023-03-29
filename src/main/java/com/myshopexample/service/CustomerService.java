package com.myshopexample.service;

import com.myshopexample.model.bascket.Bascket;
import com.myshopexample.model.customer.Customer;
import com.myshopexample.model.FavoriteList;
import com.myshopexample.model.customer.Role;
import com.myshopexample.model.dto.customer.CustomerInputDto;
import com.myshopexample.model.dto.customer.CustomerOutputDto;
import com.myshopexample.model.mapper.CustomerMapper;
import com.myshopexample.repositories.CustomerRepository;
import com.myshopexample.responses.GenericResponse;
import com.myshopexample.service.bascket.BascketService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional
public class CustomerService {
    private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BascketService bascketService;
    @Autowired
    private FavoriteListService favoriteListService;

    public GenericResponse<CustomerOutputDto> saveNewCustomer(CustomerInputDto customerInputDto, Role role){
        Customer customer = customerMapper.CustomerDtoToCustomer(customerInputDto);
        if(customerRepository.findByName(customer.getName()) == null){
            customer.setRole(role);
            customerRepository.save(customer);
            Bascket bascket = new Bascket();
            bascket.setCustomer(customer);
            bascketService.saveBascket(bascket);
            FavoriteList favoriteList = new FavoriteList();
            favoriteList.setCustomer(customer);
            favoriteListService.saveFavoriteList(favoriteList);
            CustomerOutputDto customerOutputDto = customerMapper.CustomerToCustomerOutputDto(customer);
            return new GenericResponse<>("registration successful", customerOutputDto);
        }else{
            return new GenericResponse<>("already exist an user with same name!", null);
        }
    }

    public Customer findById(Long customerId) {
        return customerRepository.findById(customerId).get();
    }

//    public GenericResponse<CustomerOutputDto> login(String customerName, String customerPassword, HttpServletRequest request) {
//        Customer customer = customerRepository.findByNameAndPassword(customerName,customerPassword);
//        if(customer == null){
//            return new GenericResponse<>("user not found",null);
//        }else{
//            request.getSession().setAttribute("customerId",customer.getId());
//            request.getSession().setAttribute("role",customer.getRole());
//            CustomerOutputDto customerOutputDto = customerMapper.CustomerToCustomerDto(customer);
//            return new GenericResponse<>("logged in successfully",customerOutputDto);
//        }
//    }

    public Customer findByName(String customerName) {
       return customerRepository.findByName(customerName);
    }
}
