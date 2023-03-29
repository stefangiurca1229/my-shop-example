package com.myshopexample.service;

import com.myshopexample.model.FavoriteList;
import com.myshopexample.repositories.FavoriteListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FavoriteListService {
    @Autowired
    private FavoriteListRepository favoriteListRepository;
    public FavoriteList saveFavoriteList(FavoriteList favoriteList){
        return favoriteListRepository.save(favoriteList);
    }

    public FavoriteList findFavoriteListByCustomerName(String customerName) {
        return favoriteListRepository.findFavoriteListsByCustomerName(customerName);
    }

    public FavoriteList findFavoriteListByCustomerId(Long id) {
        return favoriteListRepository.findFavoriteListsByCustomerId(id);
    }
}
