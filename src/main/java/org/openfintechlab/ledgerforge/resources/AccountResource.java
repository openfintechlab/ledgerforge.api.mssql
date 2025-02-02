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
 * API resoruce for `account` resource
 * Reference:
 * 
 */
package org.openfintechlab.ledgerforge.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.jboss.logging.Logger;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountResource {
    
    private static final Logger LOGGER = Logger.getLogger(AccountResource.class);

    /**
     * This method returns all the accounts defined in the ledger
     * @return List of accounts
     */
    @GET
    public Response getAllAccountsDefinedInTheLedger() {
        LOGGER.info("Getting all accounts defined in the ledger");
        return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }

    /**
     * This method returns the details of a specific account
     * @param accountId
     * @return Specific account detail
     */
    @GET
    @Path("/{accountId}")
    public Response getSepcificAccountDetails(@PathParam("accountId") String accountId) {
        LOGGER.info("Getting account details for account: "+accountId);
        return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }


    /**
     * This method returns the details of a specific account
     * @param accountId
     * @return Specific account detail
     */
    @DELETE
    @Path("/{accountId}")
    public Response deleteSepcificAccountDetails(@PathParam("accountId") String accountId) {
        LOGGER.info("Deleting for account: "+accountId);
        return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }

    @POST
    public Response createAccount() {
        LOGGER.info("Creating account");
        return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }
    
    @PUT
    @Path("/{accountId}")
    public Response updateAccount( @PathParam("accountId") String accountId) {
        LOGGER.info("Updating account with ID: "+ accountId);
        return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }

    
}
