package com.automation.flows.trial;

import com.automation.config.ApplicationHeaderBuilder;
import com.automation.model.TenantMetadata;
import com.automation.service.ApplicationRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

@Singleton
public class AuthorizeOrganizations implements Consumer<TenantMetadata> {

    @Inject
    @RestClient
    private ApplicationRestClient applicationRestClient;
    @Inject
    private ApplicationHeaderBuilder applicationHeaderBuilder;
    @Inject
    private SendSuccessResponse sendSuccessResponse;

    @Override
    public void accept(TenantMetadata tenantMetadata) {
        List<UUID> list = applicationRestClient.listAuthorizedOrganization(applicationHeaderBuilder.getAuthKeys(), tenantMetadata.getOrganizationId());
        list.add(tenantMetadata.getId());
        applicationRestClient.authorizeNewTenant(applicationHeaderBuilder.getAuthKeys(), tenantMetadata.getOrganizationId(), list);

        sendSuccessResponse.send(tenantMetadata);
    }
}
