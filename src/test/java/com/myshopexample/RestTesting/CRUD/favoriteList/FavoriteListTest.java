package com.myshopexample.RestTesting.CRUD.favoriteList;

import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FavoriteListTest {
    private final String url = "http://localhost:8080/";
    @Test
    public void getFavoriteProducts(CookieFilter cookieFilter, int expectedCode){
        RestAssured
                .given()
                .filter(cookieFilter)
                .baseUri(url+"get-favorite-products")
                .when()
                .get()
                .then()
                .log()
                .body()
                .assertThat()
                .statusCode(expectedCode);
    }
    @Test
    public void removeProductFromFavoriteListByCustomerId(CookieFilter cookieFilter, Long productId, int expectedCode){
        RestAssured
                .given()
                .filter(cookieFilter)
                .baseUri(url+"remove-product-from-favoriteList")
                .queryParam("productId", productId)
                .when()
                .delete()
                .then()
                .log()
                .body()
                .assertThat()
                .statusCode(expectedCode);
    }
    @Test
    public void addProductToFavoriteList(CookieFilter cookieFilter, int expectedCode){
        List<String> productsIds = new ArrayList<>(){{
            add("1");
            add("2");
            add("3");
        }};
        for(int i=0; i<productsIds.size(); i++)
            RestAssured
                    .given()
                    .filter(cookieFilter)
                    .baseUri(url+"add-product-to-fovoriteList")
                    .queryParam("productId", productsIds.get(i))
                    .contentType(ContentType.JSON)
                    .when()
                    .put()
                    .then()
                    .assertThat()
                    .statusCode(expectedCode);
    }
    @Test
    public void addProductToFavoriteList(CookieFilter cookieFilter, Long productId, int expectedCode){
        RestAssured
                .given()
                .filter(cookieFilter)
                .baseUri(url+"add-product-to-fovoriteList")
                .queryParam("productId", productId)
                .contentType(ContentType.JSON)
                .when()
                .put()
                .then()
                .assertThat()
                .statusCode(expectedCode);
    }
}
