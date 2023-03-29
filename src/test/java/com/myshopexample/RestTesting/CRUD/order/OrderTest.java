package com.myshopexample.RestTesting.CRUD.order;

import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class OrderTest {
    private final String url = "http://localhost:8080/";
    @Test
    public void sendOrder(CookieFilter cookieFilter, String endpoint, int expectedCode){
        RestAssured
                .given()
                .filter(cookieFilter)
                .baseUri(url + endpoint)
                .contentType(ContentType.JSON)
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(expectedCode);
    }
    @Test
    public void getOrders(CookieFilter cookieFilter, int expectedCode){
        RestAssured
                .given()
                .filter(cookieFilter)
                .baseUri(url+"customer-get-orders")
                .when()
                .get()
                .then()
                .log()
                .body()
                .assertThat()
                .statusCode(expectedCode);
    }
}
