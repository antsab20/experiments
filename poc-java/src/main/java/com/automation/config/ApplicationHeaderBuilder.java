package com.automation.config;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.util.Base64;

@ApplicationScoped
public class ApplicationHeaderBuilder {
    @ConfigProperty(name = "application.userName")
    private String user;
    @ConfigProperty(name = "application.password")
    private String password;

    public String getAuthKeys() {
        return "Basic " +
                Base64.getEncoder().encodeToString(
                        (
                                user + ":" + password
                        )
                                .getBytes()
                );
    }
}
