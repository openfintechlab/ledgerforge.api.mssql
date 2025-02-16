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
 * Entity Object for holding Transaction Infomration
 * Reference:
 * - https://github.com/openfintechlab/ledgerforge.api.mssql/wiki/Message-Structure
 */
package org.openfintechlab.ledgerforge.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.ForeignKey;

@Entity
@Table(name = "LEDFOR_TRANSACTION")
public class Transaction extends PanacheEntityBase {

    @Id
    @Column(name = "transaction_id", nullable = false, length = 64)
    public String transactionId;

    @Column(name = "instrumentID", nullable = false, length = 64)
    public String instrumentID;

    @Column(name = "source_trasnaction_ref", length = 128)
    public String sourceTransactionRef;

    @Column(name = "corel_trasnaction_id", length = 128)
    public String corelTransactionId;

    @Column(name = "transactionCode", nullable = false, length = 64)
    public String transactionCode;

    @Column(name = "type", length = 128)
    public String type;

    @Column(name = "crdrIndicator", length = 3)
    public String crdrIndicator;

    @Column(name = "merchantDetails", length = 255)
    public String merchantDetails;

    @Column(name = "amount", nullable = false)
    public BigDecimal amount;

    @Column(name = "currency_code_iso", nullable = false, length = 3)
    public String currencyCodeIso;

    @Column(name = "description", length = 255)
    public String description;

    @Column(name = "transaction_datetime", length = 255)
    public String transactionDatetime;

    @Column(name = "processingCode", length = 255)
    public String processingCode;

    @Column(name = "recordHash", length = 255)
    public String recordHash;

    @Column(name = "createdon", nullable = false)
    public LocalDateTime createdOn;

    @Column(name = "updatedon", nullable = false)
    public LocalDateTime updatedOn;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "instrumentID", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "FK_LEDFOR_TRANSACTION_LEDFOR_ACCOUNT"))
    // public Account account;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "currency_code_iso", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "FK_LEDFOR_TRANSACTION_LEDFOR_ISOCURRENCIES"))
    // public IsoCurrency isoCurrency;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "transactionCode", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "FK_LEDFOR_TRANSACTION_LEDFOR_TRANSACTIONTYPE"))
    // public TransactionType transactionType;
}
