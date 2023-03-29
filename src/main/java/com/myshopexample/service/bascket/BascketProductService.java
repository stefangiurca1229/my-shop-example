package com.myshopexample.service.bascket;

import com.myshopexample.model.bascket.BascketProduct;
import com.myshopexample.model.bascket.BascketProductKey;
import com.myshopexample.repositories.bascket.BascketProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BascketProductService {
    @Autowired
    private BascketProductRepository bascketProductRepository;

    public BascketProduct findById(BascketProductKey bascketProductKey) {
        if(bascketProductRepository.findById(bascketProductKey).isEmpty())
            return null;
        else
            return bascketProductRepository.findById(bascketProductKey).get();
    }

    public Set<BascketProduct> findBascketProductsByBascketId(Long basckeId){
        return bascketProductRepository.findBascketProductsByBascketId(basckeId);
    }
    public BascketProduct saveNewBascketProduct(BascketProduct bascketProduct){
        BascketProductKey bascketProductKey = bascketProduct.getBascketProductKey();
        if(bascketProductKey.getBascketId() == null || bascketProductKey.getProductId() == null) {
            bascketProductKey.setProductId(bascketProduct.getProduct().getId());
            bascketProductKey.setBascketId(bascketProduct.getBascket().getId());
        }
        return bascketProductRepository.save(bascketProduct);
    }

    public void removeBascketProduct(BascketProduct bascketProduct) {
        bascketProductRepository.delete(bascketProduct);
    }

    public void removeBascketProductsByBascket_Id(Long id) {
        bascketProductRepository.removeBascketProductsByBascketId(id);
    }
}
