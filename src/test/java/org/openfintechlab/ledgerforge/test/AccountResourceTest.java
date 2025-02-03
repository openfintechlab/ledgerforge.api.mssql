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
