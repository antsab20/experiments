package com.automation.service;

import com.automation.model.mautic.MauticEmailTokens;
import lombok.NonNull;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RegisterRestClient(configKey = "mautic-api")
public interface MauticClient {

    @POST
    @Path("/api/emails/{emailId}/contact/{contactId}/send")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response sendErrorEmail(@HeaderParam("Authorization") String authorization,
                            @PathParam String emailId,
                            @PathParam String contactId,
                            @NonNull MauticEmailTokens mauticEmailTokens);

    @POST
    @Path("/api/emails/{emailId}/contact/{contactId}/send")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response sendSuccessEmail(@HeaderParam("Authorization") String authorization,
                              @PathParam String emailId,
                              @PathParam String contactId,
                              @NonNull MauticEmailTokens mauticEmailTokens);
}
