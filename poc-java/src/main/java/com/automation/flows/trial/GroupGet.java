package com.automation.flows.trial;

import com.automation.config.ApplicationHeaderBuilder;
import com.automation.model.mautic.MauticFormAndTenantResponseTuple;
import com.automation.model.TenantMetadata;
import com.automation.model.scim.group.Group;
import com.automation.service.ScimClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logmanager.Level;
import org.jboss.logmanager.LogManager;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;
import java.util.function.Function;
import java.util.logging.Logger;

@Singleton
public class GroupGet implements Function<MauticFormAndTenantResponseTuple, TenantMetadata> {
    private static Logger LOGGER = LogManager.getLogManager().getLogger(String.valueOf(GroupGet.class));
    private static final String NAME = "NAME";

    @Inject
    @RestClient
    private ScimClient scimClient;
    @Inject
    private ApplicationHeaderBuilder applicationHeaderBuilder;

    @Override
    public TenantMetadata apply(MauticFormAndTenantResponseTuple mauticFormAndTenantResponseTuple) {
        Optional<Group> organization = scimClient.getGroups(applicationHeaderBuilder.getAuthKeys())
                    .getGroups()
                    .stream()
                    .filter(g -> g.getDisplayName().equals(NAME))
                    .findFirst();

        if (organization.isPresent()) {
            return TenantMetadata.builder()
                    .id(mauticFormAndTenantResponseTuple.getCreateTenantResponse().getGuid())
                    .organizationId(organization.get().getId())
                    .domainName(mauticFormAndTenantResponseTuple.getCreateTenantResponse().getPrimaryDomain())
                    .customer(mauticFormAndTenantResponseTuple.getCreateTenantResponse().getCustomer())
                    .email(mauticFormAndTenantResponseTuple.getMauticForm().getEmail())
                    .contactId(mauticFormAndTenantResponseTuple.getMauticForm().getContactId())
                    .build();
        } else {
            LOGGER.log(Level.ERROR, String.format("Organization %s  is missing", NAME));
            throw new RuntimeException("Organization is missing!");
        }
    }
}
