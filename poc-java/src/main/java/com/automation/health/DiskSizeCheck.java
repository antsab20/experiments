package com.automation.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import java.io.File;

@Liveness
public class DiskSizeCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {

        File file = new File("/");
        long freeSpace = file.getFreeSpace() / 1024 / 1024;

        return HealthCheckResponse.builder()
                .name("disk")
                .withData("remainingSpace", freeSpace + " MB")
                .state(freeSpace > 100)
                .build();

    }
}