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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openfintechlab.ledgerforge.entities.Account;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;

@QuarkusTest
public class AccountEntityTest {

    @Test
    @DisplayName("Test to create account entity")
    @Transactional
    void testCreateAccountEntity() {
        
        Account account = new Account();
        
        account.instrumentID        = "123456";
        account.instrumentType      = "ACCOUNt";
        account.instrunmentNumber   = "123";
        account.instrunmentToken    = "ASDASDASDLKJLASDLAKSJD";
        account.currencyCodeIso     = "AED";

        account.persist();
    }
}
