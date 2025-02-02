package org.openfintechlab.ledgerforge.test;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class AccountResourceTest {

    @Test    
    void testGetAllAccountsDefinedInTheLedger() {
        // TODO! The test cases needs to be changed
        given()
          .when().get("/account")
          .then()
             .statusCode(501);
    }

    @Test
    void testGetSepcificAccountDetails() {
        // TODO! The test cases needs to be changed
        given()
          .when().get("/account/1")
          .then()
             .statusCode(501);
    }

    @Test
    void testDeleteSepcificAccountDetails() {
        // TODO! The test cases needs to be changed
        given()
          .when().delete("/account/1")
          .then()
             .statusCode(501);
    }

    @Test
    void testUpdateAccount() {
        // TODO! The test cases needs to be changed
        given()
          .when().put("/account/1")
          .then()
             .statusCode(501);
    }

    @Test
    void testCreateAccount() {
        // TODO! The test cases needs to be changed
        given()
        .contentType("application/json")
          .when().post("/account")
          .then()
             .statusCode(501);
    }

}
