package com.myshopexample.repositories.bascket;

import com.myshopexample.model.bascket.BascketProduct;
import com.myshopexample.model.bascket.BascketProductKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface BascketProductRepository extends JpaRepository<BascketProduct, BascketProductKey> {
    Set<BascketProduct> findBascketProductsByBascketId(Long id);

    void removeBascketProductsByBascketId(Long id);
}
