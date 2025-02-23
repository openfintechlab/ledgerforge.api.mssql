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

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openfintechlab.ledgerforge.entities.Account;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("API test cases for `account` resource")
public class AccountResourceTest {
  private static String _entityID = "UNITTEST-123456";
  private static String _newEntityIDString = "UNITTEST-NEW0000001";

  @BeforeAll
  @Transactional
  static void setup() {
     if (Account.findById(_entityID) != null) {
      Account.deleteById(_entityID);
    }

    Account account = new Account();
    account.instrumentID = _entityID;
    account.instrumentType = "ACCOUNT";
    account.instrumentNumber = "123";
    account.instrumentToken = "ASDASDASDLKJLASDLAKSJD";
    account.currencyCodeIso = "AED";
    account.createdon = LocalDateTime.now();
    account.updatedon = LocalDateTime.now();
    account.persist();
    
    assertEquals(true, account.isPersistent());
  }

  @AfterAll  
  @Transactional
  static void tearDown() {
    if (Account.findById(_entityID) != null) {
      Account.deleteById(_entityID);
    }
    if (Account.findById(_newEntityIDString) != null) {
      Account.deleteById(_newEntityIDString);
    }
  }

  @Test
  @Order(1)
  @DisplayName("Test to get all accounts defined in the ledger")
  void testGetAllAccountsDefinedInTheLedgerWithAuthHeader() {
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

  }

  @Test
  @Order(2)
  @DisplayName("Test to verify if an account with id _entityID is returned")  
  void testGetAccountWithID() {
    given()
      .header("Authorization", "Bearer 123")
      .header("X-Message-ID", "MESSAGEID")
      .when()
      .get("/account/" + _entityID)
      .then()
        .statusCode(200)
        .header("Content-Type", "application/json;charset=UTF-8")
        .header("X-Reply-Timestamp", notNullValue())
        .header("X-Response-Code", "0000")
        .body("accounts[0].instrumentID", equalTo(_entityID));
  }


  @Test
  @Order(3)  
  @DisplayName("Test to update account")
  void testUpdateAccount() {    
    given()
        .header("Authorization", "Bearer 123")
        .header("X-Message-ID", "MESSAGEID")
        .contentType("application/json")
        .body("{\"instrumentNumber\":\"9999999\",\"instrumentType\":\"ACCOUNT\",\"status\":\"IN-ACTIVE\"}")
        .when().put("/account/"+ _entityID)
        .then()
        .statusCode(200)
        .header("Content-Type", "application/json;charset=UTF-8")
        .body("metadata.status", equalTo("0000"));
  }

  @Test
  @Order(4)
  @DisplayName("Test Creating a new account")
  void testCreateAccount() {
    given()
        .header("Authorization", "Bearer 123")
        .header("X-Message-ID", "MESSAGEID")
        .contentType("application/json")
        .body("{\"instrumentID\":\""+_newEntityIDString+"\",\"instrunmentToken\":\"token123\",\"instrumentNumber\":\"987654321001\",\"instrumentType\":\"typeA\",\"instrumentStandNumber\":\"stand123\",\"providerId\":\"provider001\",\"linkedTo\":\"linkedAccount001\",\"instrumentHash\":\"hash123\",\"personID\":\"person001\",\"personType\":\"individual\",\"currencyCodeIso\":\"USD\",\"status\":\"ACTIVE\",\"recordHash\":\"recordHash123\",\"createdon\":\"2025-02-22T10:00:00\",\"updatedon\":\"2025-02-22T10:00:00\"}")
        .when().post("/account")
        .then()
        .statusCode(200)
        .header("Content-Type", "application/json;charset=UTF-8")
        .header("X-Reply-Timestamp", notNullValue())
        .header("X-Response-Code", "0000")
        .body("accounts", not(empty()));
  }

  @Test
  @Order(5)  
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


  @Test  
  @Order(6)
  @DisplayName("Test to delete specific account")
  void testDeleteSpecificAccount() {
    given()
        .header("Authorization", "Bearer 123")
        .header("X-Message-ID", "MESSAGEID")
        .when().delete("/account/" + _entityID)
        .then()
        .statusCode(200)
        .header("Content-Type", "application/json;charset=UTF-8")
        .header("X-Reply-Timestamp", notNullValue())
        .header("X-Response-Code", "0000")
        .body("metadata.status", equalTo("0000"));
  }

}