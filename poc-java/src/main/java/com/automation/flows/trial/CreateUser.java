package com.automation.flows.trial;

import com.automation.config.ApplicationHeaderBuilder;
import com.automation.mapper.EmailToScimUserMapper;
import com.automation.model.scim.user.ScimUser;
import com.automation.model.mautic.MauticForm;
import com.automation.service.ScimClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logmanager.Level;
import org.jboss.logmanager.LogManager;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.CompletableFuture;


@Singleton
public class CreateUser {
    private static Logger LOGGER = LogManager.getLogManager().getLogger(String.valueOf(CreateUser.class));

    @Inject
    @RestClient
    private ScimClient scimClient;
    @Inject
    private EmailToScimUserMapper emailToScimUserMapper;
    @Inject
    private ApplicationHeaderBuilder applicationHeaderBuilder;
    @Inject
    private SendErrorResponse sendErrorResponse;
    @Inject
    private GroupGet groupGet;
    @Inject
    private CreateTenant createTenant;
    @Inject
    private AuthorizeOrganizations authorizeOrganizations;
    @Inject
    private HandleApplicationErrors handleApplicationErrors;

    public void apply(MauticForm form) {
        LOGGER.log(Level.INFO, String.format("Mautic form %s", form.toString()));
        boolean userAlreadyExists = scimClient.getUsers(applicationHeaderBuilder.getAuthKeys())
                .getUsers()
                .stream()
                .anyMatch(user -> user.getEmails() != null && user.getEmails().getEmailAddress().equals(form.getEmail()));

        if (userAlreadyExists) {
            LOGGER.log(Level.ERROR, String.format("User %s already exists", form.getEmail()));
            sendErrorResponse.send(form);
        } else {
            ScimUser scimUser = emailToScimUserMapper.map(form.getEmail());
            scimClient.addUser(applicationHeaderBuilder.getAuthKeys(), scimUser);
            CompletableFuture.completedFuture(form)
                    .thenApply(createTenant)
                    .thenApply(groupGet)
                    .thenAccept(authorizeOrganizations)
                    .exceptionally(handleApplicationErrors.handleException(form));
        }
    }
}
