package com.myshopexample.RestTesting.CRUD.customer;

import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CustomerTest {
    private final String url = "http://localhost:8080/";
    @Test
    public CookieFilter saveNewCustomer(int expectedCode, String customerJsonBody){
        CookieFilter cookieFilter = new CookieFilter();

            RestAssured
                    .given()
                    .filter(cookieFilter)
                    .baseUri(url + "save-new-customer")
                    .contentType(ContentType.JSON)
                    .body(customerJsonBody)
                    .when()
                    .post()
                    .then()
                    .log()
                    .body()
                    .assertThat()
                    .statusCode(expectedCode);
        return cookieFilter;
    }
}
