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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Table(name = "LEDFOR_ACCOUNT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Data
@EqualsAndHashCode(callSuper = false)
public class Account extends PanacheEntityBase {

    @Id
    @Column(name = "instrumentID", nullable = false)        
    public String instrumentID;

    @Column(name = "instrumentToken")
    public String instrumentToken;

    @Column(name = "instrumentNumber")
    public String instrumentNumber;

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

    public void setInstrumentID(String instrumentID) {
        if (instrumentID != null) {
            this.instrumentID = instrumentID;
        }
    }

    public void setInstrumentToken(String instrunmentToken) {
        if (instrunmentToken != null) {
            this.instrumentToken = instrunmentToken;
        }
    }

    public void setInstrumentNumber(String instrunmentNumber) {
        if (instrunmentNumber != null) {
            this.instrumentNumber = instrunmentNumber;
        }
    }

    public void setInstrumentType(String instrumentType) {
        if (instrumentType != null) {
            this.instrumentType = instrumentType;
        }
    }

    public void setInstrumentStandNumber(String instrumentStandNumber) {
        if (instrumentStandNumber != null) {
            this.instrumentStandNumber = instrumentStandNumber;
        }
    }

    public void setProviderId(String providerId) {
        if (providerId != null) {
            this.providerId = providerId;
        }
    }

    public void setLinkedTo(String linkedTo) {
        if (linkedTo != null) {
            this.linkedTo = linkedTo;
        }
    }

    public void setInstrumentHash(String instrumentHash) {
        if (instrumentHash != null) {
            this.instrumentHash = instrumentHash;
        }
    }

    public void setPersonID(String personID) {
        if (personID != null) {
            this.personID = personID;
        }
    }

    public void setPersonType(String personType) {
        if (personType != null) {
            this.personType = personType;
        }
    }

    public void setCurrencyCodeIso(String currencyCodeIso) {
        if (currencyCodeIso != null) {
            this.currencyCodeIso = currencyCodeIso;
        }
    }

    public void setStatus(String status) {
        if (status != null) {
            this.status = status;
        }
    }

    public void setRecordHash(String recordHash) {
        if (recordHash != null) {
            this.recordHash = recordHash;
        }
    }

    public void setCreatedon(LocalDateTime createdon) {
        if (createdon != null) {
            this.createdon = createdon;
        }
    }

    public void setUpdatedon(LocalDateTime updatedon) {
        if (updatedon != null) {
            this.updatedon = updatedon;
        }
    }

    public String getInstrumentID() {
        return instrumentID;
    }

    public String getInstrumentToken() {
        return instrumentToken;
    }

    public String getInstrumentNumber() {
        return instrumentNumber;
    }

    public String getInstrumentType() {
        return instrumentType;
    }

    public String getInstrumentStandNumber() {
        return instrumentStandNumber;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getLinkedTo() {
        return linkedTo;
    }

    public String getInstrumentHash() {
        return instrumentHash;
    }

    public String getPersonID() {
        return personID;
    }

    public String getPersonType() {
        return personType;
    }

    public String getCurrencyCodeIso() {
        return currencyCodeIso;
    }

    public String getStatus() {
        return status;
    }

    public String getRecordHash() {
        return recordHash;
    }

    public LocalDateTime getCreatedon() {
        return createdon;
    }

    public LocalDateTime getUpdatedon() {
        return updatedon;
    }
}

