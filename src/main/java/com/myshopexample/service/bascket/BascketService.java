package com.myshopexample.service.bascket;

import com.myshopexample.model.bascket.Bascket;
import com.myshopexample.model.customer.Customer;
import com.myshopexample.repositories.CustomerRepository;
import com.myshopexample.repositories.bascket.BascketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BascketService {
    @Autowired
    private BascketRepository bascketRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BascketProductService bascketProductService;
    public Bascket getBascketById(Long id){
        return bascketRepository.findById(id).get();
    }

    public Bascket saveBascket(Bascket bascket){
        return bascketRepository.save(bascket);
    }

    public Bascket getBascketByCustomerId(Long customerId) {
        return bascketRepository.findById(customerId).get();
    }

    public void removeBascket(Long customerId){
        Bascket bascket = bascketRepository.findBasketByCustomer_Id(customerId);
        bascket.removeAllBascketProducts();
        bascketProductService.removeBascketProductsByBascket_Id(bascket.getId());
    }

    public Bascket getBascketByCustomerName(String customerName) {
        Customer customer = customerRepository.findByName(customerName);
        return bascketRepository.findBasketByCustomer_Id(customer.getId());
    }
}
