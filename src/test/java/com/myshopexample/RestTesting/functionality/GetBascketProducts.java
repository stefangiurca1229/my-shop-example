package com.myshopexample.RestTesting.functionality;

import com.myshopexample.RestTesting.CRUD.bascket.BascketTest;
import com.myshopexample.RestTesting.login.LoginTest;
import io.restassured.filter.cookie.CookieFilter;
import org.junit.jupiter.api.Test;

public class GetBascketProducts {
    @Test
    public void GetBascketProducts(){
        LoginTest loginTest = new LoginTest();
        BascketTest bascketTest = new BascketTest();
        CookieFilter cookieFilter = loginTest.logInUser("user","user");
        bascketTest.getBascketProducts(cookieFilter,200);
    }
}
