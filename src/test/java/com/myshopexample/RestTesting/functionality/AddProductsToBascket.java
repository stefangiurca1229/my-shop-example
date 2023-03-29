package com.myshopexample.RestTesting.functionality;

import com.myshopexample.RestTesting.CRUD.bascket.BascketTest;
import com.myshopexample.RestTesting.CRUD.favoriteList.FavoriteListTest;
import com.myshopexample.RestTesting.login.LoginTest;
import io.restassured.filter.cookie.CookieFilter;
import org.junit.jupiter.api.Test;

public class AddProductsToBascket {
    @Test
    public void AddProductsToBascket(){
        LoginTest loginTest = new LoginTest();
        CookieFilter cookieFilter = loginTest.logInUser("user","user");
        BascketTest bascketTest = new BascketTest();
        bascketTest.addProductToBascket(cookieFilter,200);
    }

    @Test
    public void addInexistentProductToBascket(){
        LoginTest loginTest = new LoginTest();
        CookieFilter cookieFilter = loginTest.logInUser("user","user");
        BascketTest bascketTest = new BascketTest();
        bascketTest.addProductToBascket(cookieFilter,2000L,10,500);
    }

}
