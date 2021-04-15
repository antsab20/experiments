package com.automation.service;

import com.automation.model.scim.user.ScimUser;
import com.automation.model.scim.user.UsersResponse;
import com.automation.model.scim.group.GroupsResponse;
import lombok.NonNull;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RegisterRestClient(configKey = "scim-api")
public interface ScimClient {

    @POST
    @Consumes("application/scim+json")
    @Produces("application/scim+json")
    @Path("/Users")
    Response addUser(@HeaderParam("Authorization") String authorization, @NonNull ScimUser scimUser);

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/scim+json")
    @Path("/Users")
    UsersResponse getUsers(@HeaderParam("Authorization") String authorization);


    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/scim+json")
    @Path("/Groups")
    GroupsResponse getGroups(@HeaderParam("Authorization") String authorization);
}
