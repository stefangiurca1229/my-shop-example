package com.myshopexample.RestTesting.functionality;

import com.myshopexample.RestTesting.CRUD.favoriteList.FavoriteListTest;
import com.myshopexample.RestTesting.login.LoginTest;
import io.restassured.filter.cookie.CookieFilter;
import org.junit.jupiter.api.Test;

public class RemoveFromFavoriteList {
    @Test
    public void removeProductFromfavoriteList(){
        LoginTest loginTest = new LoginTest();
        FavoriteListTest favoriteListTest = new FavoriteListTest();
        CookieFilter cookieFilter = loginTest.logInUser("user","user");
        favoriteListTest.removeProductFromFavoriteListByCustomerId(cookieFilter,1L,200);
    }
    @Test
    public void removeInexistentProductFromFavoriteList(){
        LoginTest loginTest = new LoginTest();
        FavoriteListTest favoriteListTest = new FavoriteListTest();
        CookieFilter cookieFilter = loginTest.logInUser("user","user");
        favoriteListTest.removeProductFromFavoriteListByCustomerId(cookieFilter,100L,200);
    }
}
