package com.myshopexample.RestTesting.functionality;

import com.myshopexample.RestTesting.CRUD.order.OrderTest;
import com.myshopexample.RestTesting.login.LoginTest;
import io.restassured.filter.cookie.CookieFilter;
import org.junit.jupiter.api.Test;

public class SendOrder {
    @Test
    public void SendOrderPureJpa(){
        LoginTest loginTest = new LoginTest();
        CookieFilter cookieFilter = loginTest.logInUser("user","user");
        OrderTest orderTest = new OrderTest();
        orderTest.sendOrder(cookieFilter,"send-order-pure-jpa", 200);
    }
    @Test
    public void SendOrderToQuery(){
        LoginTest loginTest = new LoginTest();
        CookieFilter cookieFilter = loginTest.logInUser("user","user");
        OrderTest orderTest = new OrderTest();
        orderTest.sendOrder(cookieFilter,"send-order-with-querys",200);
    }
}
