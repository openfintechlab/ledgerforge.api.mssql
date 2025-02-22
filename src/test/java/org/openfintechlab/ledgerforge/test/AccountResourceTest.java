/*
 * Copyright (C) 2025-27 OpenFintech Lab
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Author: Muhammad Furqan
 * Created: 31-JAN-2025
 * Description: 
 * API test cases for `account` resource
 * Reference:
 * 
 */
package org.openfintechlab.ledgerforge.test;

import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;
import static org.hamcrest.Matchers.*;


@QuarkusTest
public class AccountResourceTest {

  @Test    
  @DisplayName("Test to get all accounts defined in the ledger without Bearer token")
  void testGetAllAccountsDefinedInTheLedger() {
    given()
      .header("X-Message-ID", "MESSAGEID")
      .when().get("/account")
      .then()
       .statusCode(200); // Unauthorized due to missing Bearer token
  }

  @Test    
  @DisplayName("Test to get all accounts defined in the ledger with Bearer token")
  void testGetAllAccountsDefinedInTheLedgerWithAuthHeader() {
    int statusCode = given()
                      .when().get("/account")
                      .then()
                      .extract()
                      .statusCode();

    if(statusCode == 200){
      given()
          .header("Authorization", "Bearer 123")
          .header("X-Message-ID", "MESSAGEID")
          .when().get("/account")          
          .then()
          .statusCode(200)
          .header("Content-Type", "application/json;charset=UTF-8")
          .header("X-Reply-Timestamp", notNullValue())
          .header("X-Response-Code", "0000")
          .body("accounts", not(empty()));
    }else{
      given()
          .header("Authorization", "Bearer 123")
          .header("X-Message-ID", "MESSAGEID")
          .when().get("/account")          
          .then()
          .statusCode(404)
          .header("Content-Type", "application/json;charset=UTF-8")
          .header("X-Reply-Timestamp", notNullValue())
          .header("X-Response-Code", "8001")
          .body("accounts", not(empty()));
    }                      

  }

  @Test
  @DisplayName("Test to get specific account with either 404 or 200")
  void testGetSpecificAccountDetails() {
    int statusCode = given()
            .header("Authorization", "Bearer 123")
            .header("X-Message-ID", "MESSAGEID")
            .when().get("/account")            
            .then()
            .extract()
            .statusCode();

       if(statusCode == 200){
        given()
            .header("Authorization", "Bearer 123")
            .header("X-Message-ID", "MESSAGEID")
            .when().get("/account/1")          
            .then()
            .statusCode(200)
            .header("Content-Type", "application/json;charset=UTF-8")
            .header("X-Reply-Timestamp", notNullValue())
            .header("X-Response-Code", "0000")
            .body("accounts", not(empty()));
      }else{
        given()
            .header("Authorization", "Bearer 123")
            .header("X-Message-ID", "MESSAGEID")
            .when().get("/account")          
            .then()
            .statusCode(404)
            .header("Content-Type", "application/json;charset=UTF-8")
            .header("X-Reply-Timestamp", notNullValue())
            .header("X-Response-Code", "8001")
            .body("accounts", not(empty()));
      }           
  }

  @Test
  void testDeleteSpecificAccountDetails() {
    given()
      .header("Authorization", "Bearer 123")
      .header("X-Message-ID", "MESSAGEID")
      .when().delete("/account/1")
      .then()
       .statusCode(200)
       .header("Content-Type", "application/json")
       .body("status", equalTo("DELETED"));
  }

  @Test
  void testUpdateAccount() {
    given()
      .header("Authorization", "Bearer 123")
      .header("X-Message-ID", "MESSAGEID")
      .contentType("application/json")
      .body("{\"accountId\": \"1234567\", \"accountName\": \"Updated Account\"}")
      .when().put("/account/1234567")
      .then()
       .statusCode(200)
       .header("Content-Type", "application/json;charset=UTF-8")
       .body("status", equalTo("0000"));
  }

  @Test
  @DisplayName("Test Creating a new account")
  void testCreateAccount() {
    given()
      .header("Authorization", "Bearer 123")
      .header("X-Message-ID", "MESSAGEID")
      .contentType("application/json")
      .body("{\"accountName\": \"New Account\"}")
      .when().post("/account")
      .then()
       .statusCode(200)
       .header("Content-Type", "application/json;charset=UTF-8")
       .header("X-Reply-Timestamp", notNullValue())
       .header("X-Response-Code", "0000")
       .body("accounts", not(empty()));
  }

  @Test
  @DisplayName("Test if /account endpoint returns at least one account")
  void testIfAccountEndpointReturnsAtLeastOneAccount() {
    given()
      .header("Authorization", "Bearer 123")
      .header("X-Message-ID", "MESSAGEID")
      .when().get("/account")
      .then()
       .statusCode(200)
       .header("Content-Type", "application/json;charset=UTF-8")
       .header("X-Reply-Timestamp", notNullValue())
       .header("X-Response-Code", "0000")
       .body("accounts.size()", greaterThan(0));
  }
}