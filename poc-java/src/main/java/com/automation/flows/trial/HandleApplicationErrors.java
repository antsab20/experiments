package com.automation.flows.trial;

import com.automation.model.mautic.MauticForm;
import org.jboss.logmanager.Level;
import org.jboss.logmanager.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.function.Function;

@Singleton
public class HandleApplicationErrors {

    private static Logger LOGGER = Logger.getLogger(String.valueOf(HandleApplicationErrors.class));

    @Inject
    private SendErrorResponse sendErrorResponse;

    public <T> Function<Throwable, T> handleException(MauticForm form) {
        return throwable -> {
            LOGGER.log(Level.ERROR, String.format("An error occurred %s", throwable.getMessage()));
            sendErrorResponse.send(form);
            return null;
        };
    }
}
