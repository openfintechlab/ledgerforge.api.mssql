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
 * Entity Class for defining the Metadata of the message
 * Reference:
 * 
 */

package org.openfintechlab.ledgerforge.entities;


import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class Metadata {
    
    @JsonProperty("status")
    private String              statusCode;

    @JsonProperty("description")
    private String              statusDescription;

    @JsonProperty("responseTime")
    private LocalDateTime       responseTimeStamp = LocalDateTime.now();

    public Metadata(String statusCode, String statusDescription, LocalDateTime responseTimeStamp) {
        this.statusCode         = statusCode;
        this.statusDescription  = statusDescription;
        this.responseTimeStamp  = responseTimeStamp == null ? LocalDateTime.now(): responseTimeStamp;
    }

}
