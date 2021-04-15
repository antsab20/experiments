package com.automation.flows.trial;

import com.automation.config.MauticHeaderBuilder;
import com.automation.model.mautic.MauticEmailTokens;
import com.automation.model.TenantMetadata;
import com.automation.service.MauticClient;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logmanager.Level;
import org.jboss.logmanager.LogManager;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Singleton
public class SendSuccessResponse {
    private static Logger LOGGER = LogManager.getLogManager().getLogger(String.valueOf(SendSuccessResponse.class));
    private final static String LASTNAME_TOKEN = "lastname";
    private final static String DOMAIN_TOKEN = "primaryDomain";

    @ConfigProperty(name = "mautic.success-mail-id")
    private String SUCCESS_MAIL_ID;
    @Inject
    @RestClient
    private MauticClient mauticClient;
    @Inject
    private MauticHeaderBuilder mauticHeaderBuilder;

    public void send(TenantMetadata tenantMetadata) {
        MauticEmailTokens mauticEmailTokens = getEmailTokens(tenantMetadata);
        mauticClient.sendSuccessEmail(mauticHeaderBuilder.getAuthKeys(), SUCCESS_MAIL_ID, tenantMetadata.getContactId(), mauticEmailTokens);
        LOGGER.log(Level.INFO, String.format("success %s", tenantMetadata.toString()));
    }

    private MauticEmailTokens getEmailTokens(TenantMetadata tenantMetadata) {
        MauticEmailTokens mauticEmailTokens = new MauticEmailTokens();
        Map<String, String> tokens = new HashMap<>();
        tokens.put(DOMAIN_TOKEN, tenantMetadata.getDomainName());
        tokens.put(LASTNAME_TOKEN, tenantMetadata.getCustomer());
        mauticEmailTokens.setTokens(tokens);

        return mauticEmailTokens;
    }
}
