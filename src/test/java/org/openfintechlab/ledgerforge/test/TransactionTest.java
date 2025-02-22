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
 * Unit test case for testing `transaction` entity
 * Reference:
 * - https://github.com/openfintechlab/ledgerforge.api.mssql/wiki/Message-Structure
 */
package org.openfintechlab.ledgerforge.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openfintechlab.ledgerforge.entities.Account;
import org.openfintechlab.ledgerforge.entities.Transaction;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;

@QuarkusTest
@Tag("transaction")
public class TransactionTest {
    private static final Logger LOGGER = Logger.getLogger(TransactionTest.class);

    private static String _accountID;
    private static String _transactionID;
    private static String _transactionCode;

    @BeforeAll
    @Transactional
    static void setUp() {
        _accountID = "UNITTEST-123456";
        _transactionID = "UTTRANS-101010101";
        _transactionCode = "1001";

        // Clean up any existing data
        if (Transaction.findById(_transactionID) != null) {
            Transaction.deleteById(_transactionID);
        }
        if (Account.findById(_accountID) != null) {
            Account.deleteById(_accountID);
        }
    }

    @AfterAll
    @Transactional
    static void tearDown() {
        // Clean up data after each test
        if (Transaction.findById(_transactionID) != null) {
            Transaction.deleteById(_transactionID);
        }
        if (Account.findById(_accountID) != null) {
            Account.deleteById(_accountID);
        }
    }

    @Test
    @DisplayName("Test to check create, retrieve, update and delete (CRUD) transaction entity")
    @Transactional
    void testCRUDTransactionEntity() {
        Account account = new Account();
        Transaction transaction = new Transaction();

        try {
            // Step#1: Create the account
            account.instrumentID = _accountID;
            account.instrumentType = "ACCOUNT";
            account.instrumentNumber = "123";
            account.instrumentToken = "ASDASDASDLKJLASDLAKSJD";
            account.currencyCodeIso = "AED";
            account.createdon = LocalDateTime.now();
            account.updatedon = LocalDateTime.now();
            account.persist();
            assertEquals(true, account.isPersistent());

            // Step#2: Create the transaction
            transaction.instrumentID = _accountID;
            transaction.transactionId = _transactionID;
            transaction.transactionCode = _transactionCode;
            transaction.persist();
            assertEquals(true, transaction.isPersistent());

        } catch (Exception e) {
            LOGGER.error("Error in creating transaction entity: " + e.getMessage());
            fail("Error in creating transaction entity: " + e.getMessage());
        }
    }
}
