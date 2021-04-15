package com.automation.flows.trial;

import com.automation.model.CreateTenantRequest;
import com.automation.model.CreateTenantResponse;
import com.automation.config.ApplicationHeaderBuilder;
import com.automation.model.mautic.MauticForm;
import com.automation.model.mautic.MauticFormAndTenantResponseTuple;
import com.automation.service.ApplicationRestClient;
import lombok.NonNull;
import org.apache.commons.lang3.RandomStringUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logmanager.Level;
import org.jboss.logmanager.LogManager;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.function.Function;

@Singleton
public class CreateTenant implements Function<MauticForm, MauticFormAndTenantResponseTuple> {

    @ConfigProperty(name = "application.name-length")
    private Integer DOMAIN_NAME_LENGTH;
    @ConfigProperty(name = "application.environment")
    private String ENVIRONMENT;
    @ConfigProperty(name = "application.primary-domain")
    private String PRIMARY_DOMAIN_PATTERN;

    private static Logger LOGGER = LogManager.getLogManager().getLogger(String.valueOf(CreateTenant.class));

    @Inject
    @RestClient
    private ApplicationRestClient applicationRestClient;
    @Inject
    private ApplicationHeaderBuilder applicationHeaderBuilder;

    @Override
    public MauticFormAndTenantResponseTuple apply(MauticForm mauticForm) {
        CreateTenantRequest request = createTenantRequest(mauticForm);
        LOGGER.log(Level.INFO, String.format("CreateTenantRequest: %s", request.toString()));
        CreateTenantResponse response = applicationRestClient.createTenant(applicationHeaderBuilder.getAuthKeys(), request);
        LOGGER.log(Level.INFO, String.format("CreateTenantResponse: %s", response.toString()));

        return MauticFormAndTenantResponseTuple.builder()
                .createTenantResponse(response)
                .mauticForm(mauticForm)
                .build();
    }

    @NonNull
    private CreateTenantRequest createTenantRequest(@NonNull MauticForm mauticForm) {
        return CreateTenantRequest.builder()
                .customer(mauticForm.getFirstName())
                .environment(ENVIRONMENT)
                .isTrial(true)
                .primaryDomain(MessageFormat.format(PRIMARY_DOMAIN_PATTERN, generateDomainName()))
                .domainAliases(new HashSet<>())
                .additionalApplications(new ArrayList<>())
                .build();
    }

    @NonNull
    private String generateDomainName() {
        return RandomStringUtils.randomAlphabetic(DOMAIN_NAME_LENGTH).toLowerCase();
    }
}
