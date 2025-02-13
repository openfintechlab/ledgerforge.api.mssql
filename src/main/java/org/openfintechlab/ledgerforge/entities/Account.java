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
 * DTO object for holding account information
 * Reference:
 * - https://github.com/openfintechlab/ledgerforge.api.mssql/wiki/Message-Structure
 */
package org.openfintechlab.ledgerforge.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    
    
    @NotBlank(message = "Instrument Number is mandatory")
    @Size(min=10, max=46, message="Instrument Number should be set")    
    @JsonProperty("instrunmentNumber")
    private String instrunmentNumber;    
    
    @NotBlank(message = "Instrument Type is mandatory")
    @Pattern(regexp = "ACCOUNT|CREDIT_CARD|DEBIT_CARD|PREPAID_CARD|CARD", message = "Instrument Type should be according to defined values")
    // @JsonProperty("instrunmentType")
    private String instrunmentType;

    // private String instrumentToken;
    // private String instrumentStandNumber;
    // private String providerId;
    // private String linkedTo;
    // private String insturnmentHash;
    // private String personID;
    // private String personType;
    // private String currencyCodeISO;
    // private String status;
    // private String recordHash;
    // private String createdOn;
    // private String updatedOn;
}
