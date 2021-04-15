package com.automation.health;

import com.automation.config.ApplicationHeaderBuilder;
import com.automation.service.ApplicationRestClient;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Readiness
public class ApplicationConnection implements HealthCheck {

    @Inject
    @RestClient
    ApplicationRestClient applicationRestClient;

    @Inject
    ApplicationHeaderBuilder applicationHeaderBuilder;

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse
                .named("Application Admin Connection")
                .state(checkConnection())
                .build();
    }

    private boolean checkConnection() {
        return applicationRestClient.getTenants(applicationHeaderBuilder.getAuthKeys()) != null;
    }
}
