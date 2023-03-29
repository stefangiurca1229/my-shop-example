package com.myshopexample.RestTesting.CRUD.bascket;

import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BascketTest {
    private final String url = "http://localhost:8080/";
    @Test
    public void addProductToBascket(CookieFilter cookieFilter, int expectedCode){
        List<String> productsIds = new ArrayList<>(){{
            add("1");
            add("2");
            add("3");
        }};
        List<String> customersIds = new ArrayList<>(){{
            add("2");
            add("2");
            add("2");
        }};
        for(int i=0; i<customersIds.size(); i++)
            RestAssured
                    .given()
                    .filter(cookieFilter)
                    .baseUri(url+"add-product-to-bascket")
                    .queryParam("productId", productsIds.get(i))
                    .queryParam("quantity","5")
                    .contentType(ContentType.JSON)
                    .when()
                    .put()
                    .then()
                    .log()
                    .body()
                    .assertThat()
                    .statusCode(expectedCode);
    }

    @Test
    public void addProductToBascket(CookieFilter cookieFilter, Long productId, int quantity, int expectedCode){
        RestAssured
                .given()
                .filter(cookieFilter)
                .baseUri(url+"add-product-to-bascket")
                .queryParam("productId", productId)
                .queryParam("quantity",quantity)
                .contentType(ContentType.JSON)
                .when()
                .put()
                .then()
                .log()
                .body()
                .assertThat()
                .statusCode(expectedCode);
    }
    @Test
    public void removeProductFromBascket(CookieFilter cookieFilter, Long productId, int expectedCode){
        RestAssured
                .given()
                .filter(cookieFilter)
                .baseUri(url+"remove-product-from-bascket")
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
    public void getBascketProducts(CookieFilter cookieFilter, int expectedCode){
        RestAssured
                .given()
                .filter(cookieFilter)
                .baseUri(url+"get-bascket-products")
                .when()
                .get()
                .then()
                .log()
                .body()
                .assertThat()
                .statusCode(expectedCode);
    }
}
