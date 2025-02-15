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
 * This entity class is used to retrieve the response codes from application 
 * configuration file
 * Reference:
 * - https://github.com/openfintechlab/ledgerforge.api.mssql/wiki/Message-Structure
 */
package org.openfintechlab.ledgerforge.services;


import java.util.Map;

import org.eclipse.microprofile.config.ConfigProvider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.inject.Singleton;


// ConfigProvider.getConfig().getValue("appconfig.test", String.class);

@Singleton
public class ResponseCodeMappingService {

    private JsonNode statusMappingRoot = null;
    private String statusMappingJSONString = null;
    
    /**
     * This method returns the status mapping for the given key by loading the JSON translation
     * from the configuration key `appconfig.statusMappingJSON`
     * @param key The key to load from the configurations
     * @return Map of status mapping
     */
    public Map<String, String> getStatusMapping(String key) {
        // TODO: Add JSON schmea validation for the statusMappingJSON
        if(statusMappingRoot == null) {
            statusMappingJSONString = ConfigProvider.getConfig().getValue("appconfig.statusMappingJSON", String.class);
            ObjectMapper mapper = new ObjectMapper();
            try {
                if (statusMappingRoot == null ){
                    statusMappingRoot = mapper.readTree(statusMappingJSONString);
                }                 
                 String enDescription   = statusMappingRoot.get(key).get("EN").asText();
                 String arDescription   = statusMappingRoot.get(key).get("AR").asText();
                 String code            = statusMappingRoot.get(key).get("code").asText();
                 String httpCode        = statusMappingRoot.get(key).get("HTTP_CODE").asText();
                 return Map.of("EN", enDescription, "AR", arDescription, "code", code, "HTTP_CODE", httpCode);  
             } catch (JsonProcessingException e) {
                 e.printStackTrace();
             }
        }   
        return null;
    }


}
