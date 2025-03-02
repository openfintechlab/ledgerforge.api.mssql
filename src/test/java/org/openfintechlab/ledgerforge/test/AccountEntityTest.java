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
 * Unit test case for testing `account` entity
 * Reference:
 * - https://github.com/openfintechlab/ledgerforge.api.mssql/wiki/Message-Structure
 */
package org.openfintechlab.ledgerforge.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openfintechlab.ledgerforge.entities.Account;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

@QuarkusTest
@Tag("account")
public class AccountEntityTest {

    private static final Logger LOGGER = Logger.getLogger(AccountEntityTest.class);

    @Test
    @DisplayName("Test to check create, retrieve, update and delete (CRUD) account entity")
    @Transactional    
    void testCreateAccountEntity() {
        String _entityID = "UNITTEST-123456";
        try {
            // Step#1: Dry clean the entity
            if (Account.findById(_entityID) != null) {
                Account.deleteById(_entityID);
            }

            Account account = new Account();
            account.instrumentID = _entityID;
            account.instrumentType = "ACCOUNT";
            account.instrumentNumber = "123";
            account.instrumentToken = "ASDASDASDLKJLASDLAKSJD";
            account.currencyCodeIso = "AED";
            account.providerId  =   "N/A";
            account.linkedTo    = "N/A";
            account.instrumentStandNumber = "N/A";

            account.createdon = LocalDateTime.now();
            account.updatedon = LocalDateTime.now();

            account.persist();

            Account persistedAccount = Account.findById(_entityID);

            assertNotNull(persistedAccount);
            assertEquals(_entityID, persistedAccount.instrumentID);
            assertEquals("ACCOUNT", persistedAccount.instrumentType);
            assertEquals("123", persistedAccount.instrumentNumber);
            assertEquals("ASDASDASDLKJLASDLAKSJD", persistedAccount.instrumentToken);
            assertEquals("AED", persistedAccount.currencyCodeIso);

            account.instrumentType = "ACCOUNT-UPDATED";
            account.persist();

            persistedAccount = Account.findById(_entityID);
            assertEquals("ACCOUNT-UPDATED", persistedAccount.instrumentType);

            account.delete();

        } catch (Exception e) {
            LOGGER.error("Exception occurred while creating account entity: ", e);
            fail("Exception occurred while creating account entity: " + e.getMessage());
        }

    }
}
