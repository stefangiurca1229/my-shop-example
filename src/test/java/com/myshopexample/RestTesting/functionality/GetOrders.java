package com.myshopexample.RestTesting.functionality;

import com.myshopexample.RestTesting.CRUD.order.OrderTest;
import com.myshopexample.RestTesting.login.LoginTest;
import io.restassured.filter.cookie.CookieFilter;
import org.junit.jupiter.api.Test;

public class GetOrders {
    @Test
    public void GetOrders(){
        LoginTest loginTest = new LoginTest();
        OrderTest orderTest = new OrderTest();
        CookieFilter cookieFilter = loginTest.logInUser("user","user");
        orderTest.getOrders(cookieFilter,200);
    }
}
