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
 * Common route Filter class which will be triggered when ANY route is called.
 * This class will be used to validate the request headers
 * Reference:
 * - https://github.com/openfintechlab/ledgerforge.api.mssql/wiki/Message-Structure
 */
package org.openfintechlab.ledgerforge.filters;

import java.io.IOException;
import java.time.LocalDateTime;

import org.jboss.logging.Logger;
import org.openfintechlab.ledgerforge.entities.Metadata;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.Response;



@Provider
public class CommonRoutefilter implements ContainerRequestFilter {
        
    private static final Logger LOGGER = Logger.getLogger(CommonRoutefilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        LOGGER.info("Intercepted by a common filter: " + requestContext.getUriInfo().getRequestUri());
        String xAuth        = requestContext.getHeaders().getFirst("Authorization");
        String xMessageID   = requestContext.getHeaders().getFirst("X-Message-ID");
        String xCorelID     = requestContext.getHeaders().getFirst("X-Corel-ID");
        int    xPriCode     = Integer.parseInt(requestContext.getHeaders().getFirst("X-Priority-Code") == null? "0": requestContext.getHeaders().getFirst("X-Priority-Code"));
        String xChannelID   =  requestContext.getHeaders().getFirst("X-Channel-ID");
        String xSignature   = requestContext.getHeaders().getFirst("X-Signature");
        String xClientIdURI = requestContext.getHeaders().getFirst("X-Client-Identifier-URI");

        // Step#1: Parsing the extracted headers and verifying if the required information is correct
        String strResponseDescription = String.format("Authorization Token Received: %b; MessageID: %s; Coreleation ID: %s; " +
                                                        "Priority Code: %d; ChannelID: %s; Signature: %b; Client Identification URI: %s", xAuth != null, 
                                                                                                                                            xMessageID, 
                                                                                                                                            xCorelID, 
                                                                                                                                            xPriCode, 
                                                                                                                                            xChannelID, 
                                                                                                                                            xSignature != null, 
                                                                                                                                            xClientIdURI);
        LOGGER.info(strResponseDescription);
        LOGGER.debug("Signature: " + xSignature);  
        LOGGER.debug("Authorization Token: " + xAuth);  
        requestContext.getHeaders().add("X-Reply-Timestamp", LocalDateTime.now().toString());
        if(xAuth == null || xMessageID == null ) {
            LOGGER.error(String.format("One or more required headers are missing. Returning %s Bad Request", (xAuth == null) ? "403 Forbidden":"400 Bad Request"));
            Metadata metadata = new Metadata("8400", "One or more required headers are missing. Returning 400 Bad Request", LocalDateTime.now());
            requestContext.getHeaders().add("X-Response-Code", "8400");
            requestContext.abortWith(Response.status((xAuth == null) ? Response.Status.FORBIDDEN : Response.Status.BAD_REQUEST)
                                        .entity(metadata)
                                        .header("X-Reply-Timestamp", LocalDateTime.now().toString())
                                        .header("X-Response-Code", "8400")
                                        .build());
        }
        // Sterp#2: Authenticate the request token
    }
}
