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

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Table(name = "LEDFOR_ACCOUNT")
public class Account extends PanacheEntityBase {

    @Id
    @Column(name = "instrumentID", nullable = false)        
    public String instrumentID;

    @Column(name = "instrunmentToken")
    public String instrunmentToken;

    @Column(name = "instrumentNumber")
    public String instrunmentNumber;

    @Column(name = "instrumentType")
    public String instrumentType;

    @Column(name = "instrumentStandNumber")
    public String instrumentStandNumber;

    @Column(name = "providerId")
    public String providerId;

    @Column(name = "linkedTo")
    public String linkedTo;

    @Column(name = "instrumentHash")
    public String instrumentHash;

    @Column(name = "personID")
    public String personID;

    @Column(name = "personType")
    public String personType;

    @Column(name = "currency_code_iso")
    public String currencyCodeIso;

    @Column(name = "status")
    public String status;

    @Column(name = "recordHash")
    public String recordHash;

    @Column(name = "createdon")
    public LocalDateTime createdon;

    @Column(name = "updatedon")
    public LocalDateTime updatedon;
}

