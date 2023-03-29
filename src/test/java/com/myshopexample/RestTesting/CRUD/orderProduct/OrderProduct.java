package com.myshopexample.RestTesting.CRUD.orderProduct;

import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;
import org.junit.jupiter.api.Test;

public class OrderProduct {
    private final String url = "http://localhost:8080/";

    @Test
    public void getOrderProducts(CookieFilter cookieFilter, int orderId, int expectedCode){
        RestAssured
                .given()
                .baseUri(url+"get-orderProducts")
                .queryParam("orderId", orderId)
                .when()
                .get()
                .then()
                .log()
                .body()
                .assertThat()
                .statusCode(expectedCode);
    }
}
