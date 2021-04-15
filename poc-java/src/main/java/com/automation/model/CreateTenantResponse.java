package com.automation.model;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class CreateTenantResponse {
    private UUID guid;
    private String customer;
    private String environment;
    private String primaryDomain;
    private Set<String> domainAliases;
    private String organizationIdentifier;
}
