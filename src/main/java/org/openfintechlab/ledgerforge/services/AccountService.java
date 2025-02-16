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

import org.openfintechlab.ledgerforge.entities.Account;
import org.openfintechlab.ledgerforge.entities.dto.AccountDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AccountService {

    @Inject
    ResponseCodeMappingService retMap = new ResponseCodeMappingService();

    public Map<String, Object> getAllAccounts(){
        Map<String, Object> response = new HashMap<>();
        AccountDTO accountDTO = new AccountDTO();
        List<Account> lstAccounts =  Account.listAll();
        Map<String,String> statusCode = null;
        if(lstAccounts == null || lstAccounts.size() <= 0){                    
            statusCode = retMap.getStatusMapping("BERR_NO_RECORD_FOUND");
            accountDTO.setAccounts(null);
            response.put("accountDTO", accountDTO);
            response.put("responseCode", Integer.parseInt(statusCode.get("HTTP_CODE")));
            response.put("statusCode", statusCode.get("code"));
            return response;
        }
        statusCode = retMap.getStatusMapping("SUCCESS");
        accountDTO.setMetadata(statusCode.get("code"), statusCode.get("EN"), null);
        accountDTO.setAccounts(lstAccounts);
        
        response.put("accountDTO", accountDTO);
        response.put("responseCode", Integer.parseInt(statusCode.get("HTTP_CODE")));
        response.put("statusCode", statusCode.get("code"));
        return response;
    }
   

}
