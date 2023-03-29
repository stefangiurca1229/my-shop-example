package com.myshopexample.RestTesting.functionality;

import com.myshopexample.RestTesting.CRUD.bascket.BascketTest;
import com.myshopexample.RestTesting.login.LoginTest;
import io.restassured.filter.cookie.CookieFilter;
import org.junit.jupiter.api.Test;

public class RemoveFromBascket {
    @Test
    public void removeProductFromBascket(){
        LoginTest loginTest = new LoginTest();
        BascketTest bascketTest = new BascketTest();
        CookieFilter cookieFilter = loginTest.logInUser("user","user");
        bascketTest.removeProductFromBascket(cookieFilter,1L,200);
    }
    @Test
    public void removeInexistentProductFromBascket(){
        LoginTest loginTest = new LoginTest();
        BascketTest bascketTest = new BascketTest();
        CookieFilter cookieFilter = loginTest.logInUser("user","user");
        bascketTest.removeProductFromBascket(cookieFilter,100L,500);
    }
}
