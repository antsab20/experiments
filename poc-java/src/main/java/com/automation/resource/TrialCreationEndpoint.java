package com.automation.resource;

import com.automation.flows.trial.SendErrorResponse;
import com.automation.mapper.RequestToMauticFormMapper;
import com.automation.model.mautic.MauticForm;
import com.automation.flows.trial.CreateUser;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;

@Path("/create-trial")
public class TrialCreationEndpoint {

    @Inject
    private CreateUser createUser;
    @Inject
    private RequestToMauticFormMapper requestToMauticFormMapper;
    @Inject
    private SendErrorResponse sendErrorResponse;

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public void createTrialTenant(MultivaluedMap map) {
        MauticForm mauticForm = requestToMauticFormMapper.map(map);
        try {
            createUser.apply(mauticForm);
        } catch (RuntimeException ex) {
            sendErrorResponse.send(mauticForm);
        }
    }
}
