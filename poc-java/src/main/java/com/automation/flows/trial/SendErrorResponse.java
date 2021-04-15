package com.automation.flows.trial;

import com.automation.config.MauticHeaderBuilder;
import com.automation.model.mautic.MauticEmailTokens;
import com.automation.model.mautic.MauticForm;
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
public class SendErrorResponse {
    private static Logger LOGGER = LogManager.getLogManager().getLogger(String.valueOf(SendErrorResponse.class));
    private final static String LASTNAME_TOKEN = "lastname";

    @ConfigProperty(name = "mautic.error-mail-id")
    private String ERROR_MAIL_ID;
    @Inject
    @RestClient
    private MauticClient mauticClient;
    @Inject
    private MauticHeaderBuilder mauticHeaderBuilder;

    public void send(MauticForm form) {
        MauticEmailTokens mauticEmailTokens = getEmailTokens(form);
        mauticClient.sendErrorEmail(mauticHeaderBuilder.getAuthKeys(), ERROR_MAIL_ID, form.getContactId(), mauticEmailTokens);
        LOGGER.log(Level.ERROR, String.format("Failed %s", form.getContactId()));
    }

    private MauticEmailTokens getEmailTokens(MauticForm form) {
        MauticEmailTokens mauticEmailTokens = new MauticEmailTokens();
        Map<String, String> tokens = new HashMap<>();
        tokens.put(LASTNAME_TOKEN, form.getFirstName());
        mauticEmailTokens.setTokens(tokens);

        return mauticEmailTokens;
    }
}
