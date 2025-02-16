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
 * Set Metadata for the JSON response
 * Reference:
 * 
 */

package org.openfintechlab.ledgerforge.entities.dto;

import java.time.LocalDateTime;
import java.util.List;
import org.openfintechlab.ledgerforge.entities.Account;
import org.openfintechlab.ledgerforge.entities.Metadata;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AccountDTO {
    
    @JsonProperty("metadata")
    private Metadata        metadata;

    @JsonProperty("accounts")
    private List<Account>   accounts;

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void setMetadata(String status, String description, LocalDateTime responseTime){
        this.metadata =  new Metadata(status, description, responseTime);
    }
}
