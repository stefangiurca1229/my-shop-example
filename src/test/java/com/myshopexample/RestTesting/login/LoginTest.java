package com.myshopexample.RestTesting.login;

import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;
import org.junit.jupiter.api.Test;

public class LoginTest {
    @Test
    public CookieFilter logInUser(String userName, String password){
        CookieFilter cookieFilter = new CookieFilter();

        RestAssured
                .given()
                .filter(cookieFilter)
                .baseUri("http://localhost:8080/login")
                .contentType("multipart/form-data")
                .multiPart("username", userName)
                .multiPart("password", password)
                .when()
                .post()
                .then()
                .log()
                .body()
                .assertThat()
                .statusCode(200);
        return cookieFilter;
    }
}
