package com.myshopexample.RestTesting.userRegistration;

import com.myshopexample.RestTesting.CRUD.bascket.BascketTest;
import com.myshopexample.RestTesting.CRUD.customer.CustomerTest;
import com.myshopexample.RestTesting.CRUD.order.OrderTest;
import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class UserRegistrationTest {
    private final String url = "http://localhost:8080/";
    @Test
    public void userIntegration(){
//        String customerJsonBody = """
//                {
//                "name": "ghita",
//                "password": "gita01",
//                "phone": "07******"
//                }
//                """;
//        CookieFilter cookieFilter = new CookieFilter();
//        RestAssured
//                .given()
//                .filter(cookieFilter)
//                .baseUri(url + "save-new-customer")
//                .contentType(ContentType.JSON)
//                .body(customerJsonBody)
//                .when()
//                .post()
//                .then()
//                .log()
//                .body()
//                .assertThat()
//                .statusCode(200);
    }
    @Test
    public void registerNewCustomer(){
        CustomerTest customerTest = new CustomerTest();
        String customerJsonBody = """
                {
                "name": "ghita",
                "password": "gita01",
                "phone": "07******"
                }
                """;
        customerTest.saveNewCustomer(200, customerJsonBody);
    }
}
