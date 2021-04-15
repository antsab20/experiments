package com.automation.service;

import com.automation.model.CreateTenantRequest;
import com.automation.model.CreateTenantResponse;
import com.automation.model.Tenant;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import java.util.List;
import java.util.UUID;

@RegisterRestClient(configKey = "application-api")
public interface ApplicationRestClient {

    @GET
    @Path("/admin/rest/tenants/")
    @Produces(MediaType.APPLICATION_JSON)
    List<Tenant> getTenants(@HeaderParam("Authorization") String authorization);

    @POST
    @Path("/admin/rest/tenants/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    CreateTenantResponse createTenant(@HeaderParam("Authorization") String authorization, CreateTenantRequest body);

    @GET
    @Path("/admin/rest/organization-authorizations/{organizationIdentifier}")
    @Produces(MediaType.APPLICATION_JSON)
    List<UUID> listAuthorizedOrganization(@HeaderParam("Authorization") String authorization, @PathParam String organizationIdentifier);

    @POST
    @Path("/admin/rest/organization-authorizations/{organizationIdentifier}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    List<UUID> authorizeNewTenant(@HeaderParam("Authorization") String authorization, @PathParam String organizationIdentifier, List<UUID> tenants);
}
