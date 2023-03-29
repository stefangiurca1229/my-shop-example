package com.myshopexample.repositories;

import com.myshopexample.model.FavoriteList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteListRepository extends JpaRepository<FavoriteList, Long> {
    public FavoriteList findFavoriteListsByCustomerId(Long id);

    FavoriteList findFavoriteListsByCustomerName(String customerName);
}
