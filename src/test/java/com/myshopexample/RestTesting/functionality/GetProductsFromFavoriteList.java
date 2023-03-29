package com.myshopexample.RestTesting.functionality;

import com.myshopexample.RestTesting.CRUD.bascket.BascketTest;
import com.myshopexample.RestTesting.CRUD.favoriteList.FavoriteListTest;
import com.myshopexample.RestTesting.login.LoginTest;
import io.restassured.filter.cookie.CookieFilter;
import org.junit.jupiter.api.Test;

public class GetProductsFromFavoriteList {
    @Test
    public void getProductsFromFavoriteList(){
        LoginTest loginTest = new LoginTest();
        FavoriteListTest favoriteListTest = new FavoriteListTest();
        CookieFilter cookieFilter = loginTest.logInUser("user","user");
        favoriteListTest.getFavoriteProducts(cookieFilter,200);
    }
}
