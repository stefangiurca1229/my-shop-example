package com.myshopexample.RestTesting.functionality;

import com.myshopexample.RestTesting.CRUD.favoriteList.FavoriteListTest;
import com.myshopexample.RestTesting.login.LoginTest;
import io.restassured.filter.cookie.CookieFilter;
import org.junit.jupiter.api.Test;

public class AddProductsToFavoriteList {
    @Test
    public void addProductsToFavoriteList(){
        LoginTest loginTest = new LoginTest();
        CookieFilter cookieFilter = loginTest.logInUser("user","user");
        FavoriteListTest favoriteListTest = new FavoriteListTest();
        favoriteListTest.addProductToFavoriteList(cookieFilter,200);
    }
    @Test
    public void addSameProductToFavoriteList(){
        LoginTest loginTest = new LoginTest();
        CookieFilter cookieFilter = loginTest.logInUser("user","user");
        FavoriteListTest favoriteListTest = new FavoriteListTest();
        favoriteListTest.addProductToFavoriteList(cookieFilter,200);
        favoriteListTest.addProductToFavoriteList(cookieFilter,200);
    }
    @Test
    public void addInexistentProductToFavoriteList(){
        LoginTest loginTest = new LoginTest();
        CookieFilter cookieFilter = loginTest.logInUser("user","user");
        FavoriteListTest favoriteListTest = new FavoriteListTest();
        favoriteListTest.addProductToFavoriteList(cookieFilter,2000L,500);
    }
}
