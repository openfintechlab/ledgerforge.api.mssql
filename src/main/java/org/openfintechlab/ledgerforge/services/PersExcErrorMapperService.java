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
 * Created: 22-FEB-2025
 * Description: 
 * This class is responsible for handling the database exceptions
 * Reference:
 * - https://github.com/openfintechlab/ledgerforge.api.mssql/wiki/Message-Structure
 */
package org.openfintechlab.ledgerforge.services;


import java.time.LocalDateTime;
import java.util.Map;

import org.jboss.logging.Logger;
import org.openfintechlab.ledgerforge.entities.dto.AccountDTO;
import org.openfintechlab.ledgerforge.resources.AccountResource;

import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class PersExcErrorMapperService implements ExceptionMapper<PersistenceException> {
    private static final Logger LOGGER = Logger.getLogger(AccountResource.class);
    @Inject
    ResponseCodeMappingService retMap = new ResponseCodeMappingService();

    @Override
    public Response toResponse(PersistenceException exception) {
        LOGGER.error("Database error occurred!", exception);
        Map<String, String> statusCode = null;
        statusCode = retMap.getStatusMapping("TERR_GENERAL_ERROR");
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setMetadata(statusCode.get("code"), statusCode.get("EN"), null);                
        Integer responseCode = Integer.parseInt(statusCode.get("HTTP_CODE"));
        return Response.status(responseCode)
                .entity(accountDTO)
                .header("X-Reply-Timestamp", LocalDateTime.now().toString())
                .header("X-Response-Code", statusCode.get("code"))
                .build();
    }

}