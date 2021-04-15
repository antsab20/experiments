package com.automation.health;

import com.automation.config.ApplicationHeaderBuilder;
import com.automation.service.ScimClient;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Readiness
public class ScimConnection implements HealthCheck {

    @Inject
    @RestClient
    private ScimClient scimClient;

    @Inject
    private ApplicationHeaderBuilder applicationHeaderBuilder;

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse
                .named("Scim Connection")
                .state(checkConnection())
                .build();
    }

    private boolean checkConnection() {
        try {
            return scimClient.getGroups(applicationHeaderBuilder.getAuthKeys()) != null;
        } catch (Exception e) {
            return false;
        }
    }
}
