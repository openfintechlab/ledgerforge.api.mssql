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

 * Reference:
 * - https://github.com/openfintechlab/ledgerforge.api.mssql/wiki/Message-Structure
 */


package org.openfintechlab.ledgerforge.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.jboss.logging.Logger;
import org.openfintechlab.ledgerforge.entities.Account;
import org.openfintechlab.ledgerforge.entities.dto.AccountDTO;
import org.openfintechlab.ledgerforge.resources.AccountResource;

@ApplicationScoped
public class AccountService {

    private static final Logger LOGGER = Logger.getLogger(AccountResource.class);

    @Inject
    ResponseCodeMappingService retMap = new ResponseCodeMappingService();

    /**
     * Get all accounts from the persistence layer
     * @return Map<String, Object>: accountDTO=Account DTO Object | resposneCode= HTTP Response Code | statusCode: Business Status Code
     */
    public Map<String, Object> getAllAccounts(){
        Map<String, Object> response = new HashMap<>();
        AccountDTO accountDTO = new AccountDTO();
        List<Account> lstAccounts =  Account.listAll();
        Map<String,String> statusCode = null;
        if(lstAccounts == null || lstAccounts.size() <= 0){                    
            statusCode = retMap.getStatusMapping("BERR_NO_RECORD_FOUND");
            accountDTO.setAccounts(null);

        }else{
            statusCode = retMap.getStatusMapping("SUCCESS");
            accountDTO.setAccounts(lstAccounts);
        }        
        accountDTO.setMetadata(statusCode.get("code"), statusCode.get("EN"), null);                
        response.put("accountDTO", accountDTO);
        response.put("responseCode", Integer.parseInt(statusCode.get("HTTP_CODE")));
        response.put("statusCode", statusCode.get("code"));
        return response;
    }

    /**
     * Get specific account and the response code
     * @param id Account ID to retrive from the database
     * @return Map<String, Object>: accountDTO=Account DTO Object | resposneCode= HTTP Response Code | statusCode: Business Status Code
     */
    public Map<String, Object> getAccount(String id){
        Map<String, Object> response = new HashMap<>();
        AccountDTO accountDTO = new AccountDTO();
        Account account =  Account.findById(id);
        Map<String,String> statusCode = null;
        if(account == null){
            statusCode = retMap.getStatusMapping("BERR_NO_RECORD_FOUND");
            accountDTO.setAccounts(null);
        }else{
            statusCode = retMap.getStatusMapping("SUCCESS");
            List<Account> accountList = List.of(account);
            accountDTO.setAccounts(accountList);            
        }
        
        accountDTO.setMetadata(statusCode.get("code"), statusCode.get("EN"), null);                
        response.put("accountDTO", accountDTO);
        response.put("responseCode", Integer.parseInt(statusCode.get("HTTP_CODE")));
        response.put("statusCode", statusCode.get("code"));
        return response;
    }

    /**
     * Delete specific account and return the response code
     * @param id Account ID to delete from the database
     * @return Map<String, Object>: resposneCode= HTTP Response Code | statusCode: Business Status Code
     */
    @Transactional
    public Map<String, Object> deleteAccount(String id) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> statusCode = null;
        Account account = Account.findById(id);
        AccountDTO accountDTO = new AccountDTO();

        if (account == null) {
            statusCode = retMap.getStatusMapping("BERR_NO_RECORD_FOUND");
        } else {
            account.delete();
            statusCode = retMap.getStatusMapping("SUCCESS");
        }

        accountDTO.setMetadata(statusCode.get("code"), statusCode.get("EN"), null);                
        response.put("accountDTO", accountDTO);
        response.put("responseCode", Integer.parseInt(statusCode.get("HTTP_CODE")));
        response.put("statusCode", statusCode.get("code"));
        return response;
    }

    /**
     * Create an account and return the response code
     * @param account Account to create
     * @return Map<String, Object>: resposneCode= HTTP Response Code | statusCode: Business Status Code
     */
    @Transactional
    public Map<String, Object> createAccount(Account account) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> statusCode = null;
        AccountDTO accountDTO = new AccountDTO();
        boolean isExist             = Account.findById(account.instrumentID) != null;
        boolean personAndInstExist  = Account.find("personID = ?1 and instrumentNumber = ?2", account.personID, account.instrumentNumber).firstResult() != null;
        
        if(isExist || personAndInstExist){
            LOGGER.error("Account already exist with the specified ID or personID and instrumentNumber");
            statusCode = retMap.getStatusMapping("BERR_RECORD_ALREADY_FOUND");
        }else{
            LOGGER.info("Creating account");
            account.persist();
            if(account.isPersistent()){
                statusCode = retMap.getStatusMapping("SUCCESS");
            }else{
                statusCode = retMap.getStatusMapping("TERR_UNABLE_POST");
            }
        }                        
        accountDTO.setMetadata(statusCode.get("code"), statusCode.get("EN"), null);                
        response.put("accountDTO", accountDTO);
        response.put("responseCode", Integer.parseInt(statusCode.get("HTTP_CODE")));
        response.put("statusCode", statusCode.get("code"));
        return response;
    }

    /**
     * Update an account and return the response code
     * @param id Account ID to update
     * @param account Account to update
     * @return Map<String, Object>: resposneCode= HTTP Response Code | statusCode: Business Status Code
     */
    @Transactional
    public Map<String, Object> updateAccount(String id, Account account) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> statusCode = null;
        AccountDTO accountDTO = new AccountDTO();
        Account accountToUpdate = Account.findById(id);
        if (accountToUpdate == null) {
            statusCode = retMap.getStatusMapping("BERR_NO_RECORD_FOUND");
        } else {
            accountToUpdate.setInstrumentType(account.getInstrumentType());
            accountToUpdate.setInstrumentNumber(account.getInstrumentNumber());
            accountToUpdate.setInstrumentToken(account.getInstrumentToken());
            accountToUpdate.setCurrencyCodeIso(account.getCurrencyCodeIso());
            accountToUpdate.setStatus(account.getStatus());
            accountToUpdate.setRecordHash(account.getRecordHash());
            accountToUpdate.setLinkedTo(account.getLinkedTo());
            accountToUpdate.setInstrumentHash(account.getInstrumentHash());
            accountToUpdate.setPersonID(account.getPersonID());
            accountToUpdate.setPersonType(account.getPersonType());
            accountToUpdate.setProviderId(account.getProviderId());
            accountToUpdate.setInstrumentStandNumber(account.getInstrumentStandNumber());
            accountToUpdate.setUpdatedon(account.getUpdatedon());

            accountToUpdate.persist();
            if (accountToUpdate.isPersistent()) {
                statusCode = retMap.getStatusMapping("SUCCESS");
            } else {
                statusCode = retMap.getStatusMapping("TERR_UNABLE_PUT");
            }
        }
        accountDTO.setMetadata(statusCode.get("code"), statusCode.get("EN"), null);
        response.put("accountDTO", accountDTO);
        response.put("responseCode", Integer.parseInt(statusCode.get("HTTP_CODE")));
        response.put("statusCode", statusCode.get("code"));
        return response;
    }

}
